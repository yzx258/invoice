package com.rbgt.kp.invoice.base.dto.community;

import lombok.Data;

import java.util.List;

/**
 * @author 俞春旺
 * @program: JueWei
 * @date 2021-02-16 20:23:41
 * @description: 描述
 */
@Data
public class Resp {

    private int code;

    private String msg;

    private String count;

    private List<CommunityListDto> data;

}
