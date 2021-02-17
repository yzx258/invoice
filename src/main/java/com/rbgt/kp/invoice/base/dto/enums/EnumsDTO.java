package com.rbgt.kp.invoice.base.dto.enums;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @company： 厦门宜车时代信息技术有限公司
 * @copyright： Copyright (C), 2020
 * @author： yucw
 * @date： 2020/12/28 10:07
 * @version： 1.0
 * @description: 枚举响应类
 */
@Data
public class EnumsDTO {

    /**
     * ID 唯一标识
     */
    @ApiModelProperty("code")
    private String code;

    /**
     * ID 唯一标识
     */
    @ApiModelProperty("描述")
    private String msg;

}
