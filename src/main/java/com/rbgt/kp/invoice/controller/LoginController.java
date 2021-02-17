package com.rbgt.kp.invoice.controller;

import com.rbgt.kp.invoice.base.dto.community.CommunityListDto;
import com.rbgt.kp.invoice.base.dto.community.Resp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 俞春旺
 * @program: JueWei
 * @date 2021-02-13 17:29:45
 * @description: 描述
 */
@RestController
public class LoginController {


    @GetMapping("/list.do")
    public List<CommunityListDto> list(){
        Resp resp = new Resp();
        List<CommunityListDto> list = new ArrayList<>();
        CommunityListDto d1 = new CommunityListDto();
        d1.setKpNo("KP000001");
        d1.setKpGs("厦门小黑公司");
        d1.setKpName("进货开票");
        d1.setKpUserName("小白");
        d1.setStatus("待开票");
        d1.setTime("2021年02月14日");

        CommunityListDto d2 = new CommunityListDto();
        d2.setKpNo("KP000002");
        d2.setKpGs("厦门小白公司");
        d2.setKpName("调货开票");
        d2.setKpUserName("小杨");
        d2.setStatus("待开票");
        d2.setTime("2021年02月14日");

        CommunityListDto d3 = new CommunityListDto();
        d3.setKpNo("KP000003");
        d3.setKpGs("厦门小白公司");
        d3.setKpName("货运开票");
        d3.setKpUserName("小林");
        d3.setStatus("待开票");
        d3.setTime("2021年02月14日");

        list.add(d1);
        list.add(d2);
        list.add(d3);

        resp.setCode(0);
        resp.setData(list);
        resp.setMsg("success");
        resp.setCount("5");
        return list;
    }

}
