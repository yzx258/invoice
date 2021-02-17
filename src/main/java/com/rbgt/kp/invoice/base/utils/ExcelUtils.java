package com.rbgt.kp.invoice.base.utils;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;


/**
 * @company： 厦门宜车时代信息技术有限公司
 * @copyright： Copyright (C), 2021
 * @author： yucw
 * @date： 2021/1/21 10:19
 * @version： 1.0
 * @description: POI导出工具
 */
@Slf4j
@Component
public class ExcelUtils {

    private static int firstRow = 0;
    private static int lastRow = 0;


    /**
     * 导出excel工具类
     *
     * @param excelName sheet表名称
     * @param fieldList 表头的定义集合
     * @param dataList  数据集合
     * @return
     * @throws Exception
     */
    @SuppressWarnings("deprecation")
    public static HSSFWorkbook drawExcel(String excelName, String[] fields, List<String[]> dataList) {
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(excelName);
        sheet.createFreezePane(0, 1, 0, 1);
        CellRangeAddress cra = new CellRangeAddress(0, 1, 0, fields.length - 1);
        sheet.setAutoFilter(cra);
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        HSSFFont font = wb.getFontAt((short) 0);
        font.setCharSet(HSSFFont.DEFAULT_CHARSET);
        font.setFontHeightInPoints((short) 12);//更改默认字体大小
        font.setFontName("宋体");//
        style.setFont(font);

        HSSFCellStyle style1 = wb.createCellStyle();
        HSSFFont font1 = wb.createFont();
        font1.setCharSet(HSSFFont.DEFAULT_CHARSET);
        font1.setFontHeightInPoints((short) 12);//更改默认字体大小
        font1.setFontName("宋体");//
        style1.setFont(font1);

        HSSFCell cell = null;
        for (int i = 0; i < fields.length; i++) {
            cell = row.createCell((short) i);
            cell.setCellValue(fields[i]);
            cell.setCellStyle(style);
        }
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，
        for (int i = 0; i < dataList.size(); i++) {
            row = sheet.createRow((int) i + 1);
            String[] arrs = dataList.get(i);
            // 第四步，创建单元格，并设置值
            for (int j = 0; j < arrs.length; j++) {
                cell = row.createCell((short) j);
                cell.setCellStyle(style1);
                cell.setCellValue(arrs[j]);
//                row.createCell((short) j).setCellValue(arrs[j]);
            }
        }
        return wb;
    }

    public static SXSSFWorkbook drawExcel2007(String excelName, String[] fields, List<String[]> dataList) {
        // 声明一个工作薄
        SXSSFWorkbook workBook = new SXSSFWorkbook();
        // 生成一个表格
        SXSSFSheet sheet = workBook.createSheet();
        sheet.createFreezePane(0, 1, 0, 1);
        CellRangeAddress cra = new CellRangeAddress(0, 1, 0, fields.length - 1);
        sheet.setAutoFilter(cra);
        workBook.setSheetName(0, excelName);
        CellStyle style = workBook.createCellStyle();
        Font font = workBook.getFontAt((short) 0);
        font.setCharSet(HSSFFont.DEFAULT_CHARSET);
        font.setFontHeightInPoints((short) 12);//更改默认字体大小
        font.setFontName("宋体");//
        //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(font);

        // 创建表格标题行 第一行
        SXSSFRow titleRow = sheet.createRow(0);
        for (int i = 0; i < fields.length; i++) {
            titleRow.createCell(i).setCellValue(fields[i]);
        }
        //插入需导出的数据
        for (int i = 0; i < dataList.size(); i++) {
            SXSSFRow row = sheet.createRow(i + 1);
            String[] arrs = dataList.get(i);
            // 第四步，创建单元格，并设置值
            for (int j = 0; j < arrs.length; j++) {
                SXSSFCell cells = row.createCell((short) j);
                cells.setCellStyle(style);
                cells.setCellValue(arrs[j]);
            }
        }
        return workBook;
    }

    public static SXSSFWorkbook drawExcel2018(String excelName, String[] fields, List<String[]> dataList) {
        // 声明一个工作薄
        SXSSFWorkbook workBook = new SXSSFWorkbook();
        // 生成一个表格
        SXSSFSheet sheet = workBook.createSheet();
        sheet.createFreezePane(0, 1, 0, 1);
        CellRangeAddress cra = new CellRangeAddress(0, 1, 0, fields.length - 1);
        sheet.setAutoFilter(cra);
        workBook.setSheetName(0, excelName);
        CellStyle style = workBook.createCellStyle();
        Font font = workBook.getFontAt((short) 0);
        font.setCharSet(HSSFFont.DEFAULT_CHARSET);
        font.setFontHeightInPoints((short) 12);//更改默认字体大小
        font.setFontName("宋体");//
        //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(font);

        // 创建表格标题行 第一行
        SXSSFRow titleRow = sheet.createRow(0);
        for (int i = 0; i < fields.length; i++) {
            titleRow.createCell(i).setCellValue(fields[i]);
        }
        //插入需导出的数据
        for (int i = 0; i < dataList.size(); i++) {
            SXSSFRow row = sheet.createRow(i + 1);
            String[] arrs = dataList.get(i);
            // 第四步，创建单元格，并设置值
            for (int j = 0; j < arrs.length; j++) {
                SXSSFCell cells = row.createCell((short) j);
                cells.setCellStyle(style);
                cells.setCellValue(StrUtil.isNotEmpty(arrs[j]) ? arrs[j] : "");
            }
        }
        return workBook;
    }

    /**
     * 导出excel工具类
     * 合并单元格
     *
     * @param excelName sheet表名称
     * @param fieldList 表头的定义集合
     * @param dataList  数据集合
     * @return
     * @throws Exception
     */
    @SuppressWarnings("deprecation")
    public static HSSFWorkbook drawExcelByCellRange(String excelName, String[] fields, List<String[]> dataList) {
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(excelName);
        sheet.createFreezePane(0, 1, 0, 1);
        CellRangeAddress cra = new CellRangeAddress(0, 1, 0, fields.length - 1);
        sheet.setAutoFilter(cra);
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        HSSFFont font = wb.getFontAt((short) 0);
        font.setCharSet(HSSFFont.DEFAULT_CHARSET);
        font.setFontHeightInPoints((short) 12);//更改默认字体大小
        font.setFontName("宋体");//
        style.setFont(font);

        HSSFCellStyle style1 = wb.createCellStyle();
        HSSFFont font1 = wb.createFont();
        font1.setCharSet(HSSFFont.DEFAULT_CHARSET);
        font1.setFontHeightInPoints((short) 12);//更改默认字体大小
        font1.setFontName("宋体");//
        style1.setFont(font1);

        HSSFCell cell = null;
        for (int i = 0; i < fields.length; i++) {
            cell = row.createCell((short) i);
            cell.setCellValue(fields[i]);
            cell.setCellStyle(style);
        }
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，
        try {
            for (int i = 0; i < dataList.size(); i++) {
                row = sheet.createRow((int) i + 1);
                String[] arrs = dataList.get(i);
                // 第四步，创建单元格，并设置值
                for (int j = 0; j < arrs.length; j++) {
                    String arrsJ = arrs[j] == null ? "" : arrs[j];
                    String dataListArrsJ = "";
                    if (dataList.size() > i + 1) {
                        dataListArrsJ = dataList.get(i + 1)[j] == null ? "" : arrs[j];
                    } else {
                        dataListArrsJ = "";
                    }
                    cell = row.createCell((short) j);
                    cell.setCellStyle(style1);
                    cell.setCellValue(arrs[j]);
                    // 判断和下面单元格内容是否相同
                    if (dataList.size() > (i + 1) && j == 0 && dataList.get(i + 1)[0] != null && arrs[0].equals(dataList.get(i + 1)[0])) {
                        firstRow = i + 1;
                        lastRow = i + 2;
                        sheet.addMergedRegion(new CellRangeAddress(i + 1, i + 2, j, j));//合并单元格
                    } else if (dataList.size() > (i + 1) && j != 0 && j < arrs.length - 3 && arrsJ.equals(dataListArrsJ)) {
                        sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, j, j));//合并单元格
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return wb;
    }


    public static void downExcel(HSSFWorkbook wb, HttpServletResponse response, String excelName) {
        OutputStream os = null;
        // 输出
        response.reset();
        try {
            response.addHeader("Content-Disposition", "attachment;filename=" + new String((excelName + ".xlsx").getBytes("UTF-8"), "ISO-8859-1"));
            //response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(excelName + ".xls", TygConstants.ALL_ENCODING));
            os = response.getOutputStream();
            wb.write(os);
        } catch (Exception e) {
            throw new RuntimeException("导出报表异常");
        } finally {
            try {
                if (os != null) {
                    os.flush();
                    os.close();
                }
            } catch (IOException e) {
                throw new RuntimeException("导出报表异常");
            }
        }
    }

}
