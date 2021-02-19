package com.rbgt.kp.invoice.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rbgt.kp.invoice.base.dto.community.CommunityListDto;
import com.rbgt.kp.invoice.base.dto.community.Resp;
import com.rbgt.kp.invoice.config.resoponse.ResponseResult;
import com.rbgt.kp.invoice.config.resoponse.target.BaseResponse;
import io.swagger.annotations.Api;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @company： 厦门宜车时代信息技术有限公司
 * @copyright： Copyright (C), 2021
 * @author： yucw
 * @date： 2021/2/19 10:05
 * @version： 1.0
 * @description: 开票相关接口
 */
@Api(value = "/kp", tags = "开票接口")
@RestController
public class InvoiceController {

    @ResponseBody
    @RequestMapping("/list.do")
    public Resp list(@RequestParam(name = "id", required = false) String id,int page,int limit){
        System.out.println(id);
        System.out.println(page);
        System.out.println(limit);
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
        return resp;
    }

}
