package com.rbgt.kp.invoice.base.utils.excel;

import cn.hutool.core.util.ReflectUtil;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @title:
 * @description:
 * @copyright: Copyright (c) 2020
 * @company: 厦门宜车时代信息技术有限公司
 * @version: 1.0
 * @author: 俞春旺
 * @date: 2020-08-18
 */
public class ExcelFieldUtil {


    public static Map<String, String> getClassFieldMap(Class<?> clazz) {
        Field[] fields = ReflectUtil.getFields(clazz);
        Map<String, String> fieldMap = new HashMap<>(fields.length);
        for (Field field : fields) {
            for (Annotation annotation : field.getDeclaredAnnotations()) {
                if (annotation.annotationType() == ApiModelProperty.class) {
                    Object value = AnnotationUtils.getValue(annotation);
                    fieldMap.put(field.getName(), Optional.ofNullable(value).orElse("").toString());
                }
            }
        }
        return fieldMap;
    }

}
