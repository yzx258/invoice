package com.rbgt.kp.invoice.base.dto.login;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @company： 厦门宜车时代信息技术有限公司
 * @copyright： Copyright (C), 2020
 * @author： yucw
 * @date： 2020/12/16 15:59
 * @version： 1.0
 * @description: 登录响应类
 */
@Data
public class LoginDTO {

    @ApiModelProperty("token")
    private String token;
}
