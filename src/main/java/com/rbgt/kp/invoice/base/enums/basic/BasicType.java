package com.rbgt.kp.invoice.base.enums.basic;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @company： 厦门宜车时代信息技术有限公司
 * @copyright： Copyright (C), 2020
 * @author： yucw
 * @date： 2020/12/21 17:03
 * @version： 1.0
 * @description: 基础配置类型
 */
@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum BasicType {

    Z(0,"无"),
    DAILY(1,"日结配置")
    ;

    private int code;
    private String msg;

    /**
     * 枚举转换
     * @param code
     * @return
     */
    public static BasicType getSexEnumByCode(int code){
        for(BasicType sexEnum : BasicType.values()){
            if(code == sexEnum.getCode()){
                return sexEnum;
            }
        }
        return null;
    }

}
