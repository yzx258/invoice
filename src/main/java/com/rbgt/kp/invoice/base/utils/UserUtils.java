package com.rbgt.kp.invoice.base.utils;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.rbgt.kp.invoice.base.dto.login.LoginDTO;
import com.rbgt.kp.invoice.base.enums.ResponseCode;
import com.rbgt.kp.invoice.config.handler.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @company： 厦门宜车时代信息技术有限公司
 * @copyright： Copyright (C), 2020
 * @author： yucw
 * @date： 2020/12/21 13:57
 * @version： 1.0
 * @description:
 */
@Slf4j
@Component
public class UserUtils {

    @Autowired
    private CacheUtils cacheUtils;

    /**
     * 请求上下文获取用户信息
     * @return
     */
    public String getUserId(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String token = request.getParameter("token");
        String user = cacheUtils.getCache(token);
        if(StrUtil.isNotBlank(user)){
            LoginDTO loginDTO = JSON.parseObject(cacheUtils.getCache(token), LoginDTO.class);
            return "";
        }else{
            throw new BaseException(ResponseCode.USER_NOT_ERROR1);
        }
    }
}
