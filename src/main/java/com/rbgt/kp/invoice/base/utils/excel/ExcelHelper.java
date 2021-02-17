package com.rbgt.kp.invoice.base.utils.excel;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSON;
import lombok.Cleanup;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @title: excel导出工具类
 * @description: 支持spel表达式模板导出
 * @copyright: Copyright (c) 2020
 * @company: 厦门宜车时代信息技术有限公司
 * @version: 1.0
 * @author: 俞春旺
 * @date: 2020-08-27
 */
@Component
@Slf4j
public class ExcelHelper {

    private final ApplicationContext applicationContext;

    private static ExcelHelper self;

    private static final ExpressionParser PARSER = new SpelExpressionParser();

    public ExcelHelper(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    @PostConstruct
    public void inject() {
        self = this;
    }

    @Data
    public static class ExcelTemplate {

        /**
         * 备注
         */
        private String desc;

        /**
         * excel字段列表
         */
        private List<Column> columns;

        @Data
        public static class Column {

            /**
             * 列标题
             */
            private String title;

            /**
             * 列宽
             */
            private Integer width;

            /**
             * 单元格的值
             */
            private String value;

        }
    }

    /**
     * 格式转换器
     */
    private static class ExcelFormatter {

        private ExcelFormatter() {

        }

        private static class Holder {
            private static final ExcelFormatter INSTANCE = new ExcelFormatter();
        }

        private static ExcelFormatter getInstance() {
            return Holder.INSTANCE;
        }

        private static final DateTimeFormatter DEFAULT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

        private static final Map<String, DateTimeFormatter> DATE_TIME_FORMATTER_CACHE = new ConcurrentHashMap<>();


        /**
         * 格式化日期
         *
         * @param date 日期
         */
        public String formatDate1(Date date) {
            if (date == null) {
                return "";
            }
            return DateUtil.format(date,"yyyy年MM月dd日 hh时mm分ss秒");
        }

        /**
         * 格式化日期
         *
         * @param localDateTime 日期
         */
        public String formatDate(LocalDateTime localDateTime) {
            if (localDateTime == null) {
                return "";
            }
            return localDateTime.format(DEFAULT_DATE_TIME_FORMATTER);
        }

        /**
         * 格式化日期
         *
         * @param localDateTime 日期
         * @param pattern       格式
         */
        @SneakyThrows
        public String formatDate(LocalDateTime localDateTime, String pattern) {
            if (localDateTime == null) {
                return "";
            }
            if (StringUtils.isEmpty(pattern)) {
                return localDateTime.toString();
            }
            DateTimeFormatter dateTimeFormatter = DATE_TIME_FORMATTER_CACHE.putIfAbsent(pattern, DateTimeFormatter.ofPattern(pattern));
            assert dateTimeFormatter != null;
            return localDateTime.format(dateTimeFormatter);
        }

        /**
         * 格式化BigDecimal
         *
         * @param number  数字
         * @param pattern 格式
         *                例如 3.1415927
         *                0        =   3           取一位整数
         *                0.00     =   3.14        取一位整数和两位小数
         *                00.000   =   03.142      取两位整数和三位小数，整数不足部分以0填补。
         *                #        =   3           取所有整数部分
         *                #.##%    =   314.16%     以百分比方式计数，并取两位小数
         *                ,###     =   299,792,458 每三位以逗号进行分隔
         */
        public String formatNumber(Number number, String pattern) {
            return new DecimalFormat(pattern).format(number);
        }

    }


    /**
     * 导出excel
     *
     * @param templatePath 模板路径
     * @param dataList     数据列表
     * @param response     .
     */
    public static void export(String templatePath, List<?> dataList, HttpServletResponse response) throws IOException {
        String templateStr = ResourceUtil.readUtf8Str(templatePath);
        ExcelTemplate excelTemplate = JSON.parseObject(templateStr, ExcelTemplate.class);
        export(excelTemplate, dataList, response);
    }

    /**
     * 导出excel
     *
     * @param excelTemplate 模板配置
     * @param dataList      数据列表
     * @param response      .
     */
    public static void export(ExcelTemplate excelTemplate, List<?> dataList, HttpServletResponse response) throws IOException {

        if (excelTemplate == null || CollectionUtils.isEmpty(excelTemplate.getColumns())) {
            throw new IllegalArgumentException("Excel导出模板配置不能为空");
        }

        if (CollectionUtils.isEmpty(dataList)) {
            return;
        }

        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + System.currentTimeMillis() + ".xls");

        // 构建表达式评估(求值)上下文环境,也需要指定环境中相关的变量
        StandardEvaluationContext context = new StandardEvaluationContext();
        ExcelFormatter formatter = ExcelFormatter.getInstance();
        context.setVariable("fmt", formatter);
        // 组件引用
        context.setBeanResolver(new BeanFactoryResolver(self.applicationContext));
        @Cleanup ExcelWriter writer = ExcelUtil.getWriter();

        // 设置列宽
        setColumnWidth(writer, excelTemplate);

        List<Map<String, Object>> dataMap = new ArrayList<>(dataList.size());
        for (Object data : dataList) {
            List<ExcelTemplate.Column> columns = excelTemplate.getColumns();
            Map<String, Object> rowMap = new LinkedHashMap<>(columns.size());
            context.setRootObject(data);
            for (ExcelTemplate.Column column : columns) {
                Object value = null;
                try {
                    value = PARSER.parseExpression(column.getValue()).getValue(context);
                } catch (Exception e) {
                    log.warn("获取字段值失败 , value : {}", column.getValue());
                }
                rowMap.put(column.getTitle(), value);
            }
            dataMap.add(rowMap);
        }
        writer.write(dataMap);
        writer.flush(response.getOutputStream());
    }

    /**
     * 设置列宽
     *
     * @param writer        写入对象
     * @param excelTemplate 配置
     */
    private static void setColumnWidth(ExcelWriter writer, ExcelTemplate excelTemplate) {
        List<ExcelTemplate.Column> columns = excelTemplate.getColumns();
        for (int i = 0; i < columns.size(); i++) {
            ExcelTemplate.Column column = columns.get(i);
            Integer colWidth = Optional.ofNullable(column.getWidth()).orElse(20);
            writer.setColumnWidth(i, colWidth);
        }
    }

}
