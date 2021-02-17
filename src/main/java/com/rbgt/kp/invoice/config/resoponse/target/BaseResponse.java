package com.rbgt.kp.invoice.config.resoponse.target;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 俞春旺
 * @program: JueWei
 * @date 2020-12-14 22:21:10
 * @description: 统一响应注解
 * 添加注解后，统一响应体才能生效
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface BaseResponse {

}
