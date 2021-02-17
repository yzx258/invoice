package com.rbgt.kp.invoice.config.resoponse;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 俞春旺
 * @program: JueWei
 * @date 2020-12-14 22:19:52
 * @description: 统一的公共响应体
 */
@Data
@AllArgsConstructor
public class ResponseResult<T> implements Serializable {
    /**
     * 返回状态码
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String msg;
    /**
     * 数据
     */
    private T data;

    public ResponseResult(T data) {
        this.code = 200;
        this.msg = "success";
        this.data = data;
    }
}