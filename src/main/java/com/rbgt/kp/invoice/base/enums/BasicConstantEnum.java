package com.rbgt.kp.invoice.base.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @company： 厦门宜车时代信息技术有限公司
 * @copyright： Copyright (C), 2020
 * @author： yucw
 * @date： 2020/12/15 16:54
 * @version： 1.0
 * @description: 基础配置枚举
 */
@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum BasicConstantEnum {

    Z(0,"无"),
    ;

    /**
     * 状态码
     */
    private int code;
    /**
     * 返回信息
     */
    private String msg;

}
