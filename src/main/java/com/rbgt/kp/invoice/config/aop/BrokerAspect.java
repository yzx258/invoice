package com.rbgt.kp.invoice.config.aop;

import cn.hutool.core.util.StrUtil;
import com.rbgt.kp.invoice.base.enums.ResponseCode;
import com.rbgt.kp.invoice.base.utils.CacheUtils;
import com.rbgt.kp.invoice.config.handler.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.UUID;

/**
 * @company： 厦门宜车时代信息技术有限公司
 * @copyright： Copyright (C), 2020
 * @author： yucw
 * @date： 2020/12/16 14:38
 * @version： 1.0
 * @description:
 */
@Slf4j
@Aspect
@Component
public class BrokerAspect {

    @Autowired
    private CacheUtils cacheUtils;

    /**
     * 定义切入点，切入点为com.rbgt.jw..*中的所有函数
     *通过@Pointcut注解声明频繁使用的切点表达式
     */
    @Pointcut("execution(public * com.rbgt.kp.invoice.controller..*.*(..)))")
    public void BrokerAspect(){

    }

    /**
     * 前置通知：在连接点之前执行的通知
     * @param joinPoint
     * @throws Throwable
     */
    @Before("BrokerAspect()")
    public void doBefore(JoinPoint joinPoint) {
        MDC.put("R_LOG_ID", UUID.randomUUID().toString().replace("-",""));
        log.info("进来了AOP");
        if(joinPoint.getSignature().getName().equals("login")
                || joinPoint.getSignature().getName().equals("index")
                || joinPoint.getSignature().getName().equals("list")){
            log.info("免鉴权接口："+joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
            return;
        }
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String token = request.getParameter("token");
        // 记录下请求内容
        log.info("token : " + request.getParameter("token"));
        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        if(StrUtil.isNotBlank(token)){
            String cache = cacheUtils.getCache(token);
            if(StrUtil.isBlank(cache)){
                throw new BaseException(ResponseCode.TOKEN_INVALID_ERROR);
            }
            log.info("token存在有效期，正常访问接口");
        }else{
            throw new BaseException(ResponseCode.TOKEN_NULL_ERROR);
        }
    }

    @AfterReturning(returning = "ret",pointcut = "BrokerAspect()")
    public void doAfterReturning(Object ret)  {
        // 处理完请求，返回内容
        log.info("RESPONSE : " + ret);
        MDC.remove("R_LOG_ID");
    }
}
