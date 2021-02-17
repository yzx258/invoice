package com.rbgt.kp.invoice.config.handler;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.rbgt.kp.invoice.base.enums.ResponseCode;
import com.rbgt.kp.invoice.config.resoponse.ResponseResult;
import com.rbgt.kp.invoice.config.resoponse.target.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 异常处理器
 *
 * @author NULL
 * @since 2019-07-16
 */
@ControllerAdvice(annotations = BaseResponse.class)
@ResponseBody
@Slf4j
public class ExceptionHandlerAdvice {
    /**
     * 处理未捕获的Exception
     *
     * @param e 异常
     * @return 统一响应体
     */
    @ExceptionHandler(Exception.class)
    public ResponseResult handleException(Exception e) {
        if (ObjectUtil.isNotNull(e.getMessage()) && StrUtil.isNotBlank(e.getMessage())) {
            String containChinese = isContainChinese(e.getMessage());
            log.error("处理未捕获的Exception：{}", containChinese);
            log.error("处理未捕获的Exception：{}", e);
            return new ResponseResult(ResponseCode.SERVICE_ERROR.getCode(), ResponseCode.SERVICE_ERROR.getMsg(), containChinese);
        }
        log.error("处理未捕获的Exception：{}", e.getMessage());
        log.error("处理未捕获的Exception：{}", e);
        return new ResponseResult(ResponseCode.SERVICE_ERROR.getCode(), ResponseCode.SERVICE_ERROR.getMsg(), e.getMessage());
    }

    /**
     * 处理未捕获的RuntimeException
     *
     * @param e 运行时异常
     * @return 统一响应体
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseResult handleRuntimeException(RuntimeException e) {
        if (ObjectUtil.isNotNull(e.getMessage()) && StrUtil.isNotBlank(e.getMessage())) {
            String containChinese = isContainChinese(e.getMessage());
            log.error("处理未捕获的RuntimeException：{}", containChinese);
            log.error("处理未捕获的RuntimeException：{}", e);
            return new ResponseResult(ResponseCode.SERVICE_ERROR.getCode(), ResponseCode.SERVICE_ERROR.getMsg(), containChinese);
        }
        log.error("处理未捕获的RuntimeException：{}", e.getMessage());
        log.error("处理未捕获的RuntimeException：{}", e);
        return new ResponseResult(ResponseCode.SERVICE_ERROR.getCode(), ResponseCode.SERVICE_ERROR.getMsg(), e.getMessage());
    }

    /**
     * 处理业务异常BaseException
     *
     * @param e 业务异常
     * @return 统一响应体
     */
    @ExceptionHandler(BaseException.class)
    public ResponseResult handleBaseException(BaseException e) {
        log.info("处理业务异常BaseException");
        log.error(e.getMessage(), e);
        ResponseCode code = e.getCode();
        return new ResponseResult(code.getCode(), code.getMsg(), e.getMessage());

    }

    /**
     * 字符串是否包含中文
     *
     * @param str 待校验字符串
     * @return true 包含中文字符 false 不包含中文字符
     */
    public static String isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4E00-\u9FA5|\\！|\\，|\\。|\\（|\\）|\\《|\\》|\\“|\\”|\\？|\\：|\\；|\\【|\\】]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return str.replaceAll("[^\\u4e00-\\u9fa5]", "");
        }
        return null;
    }
}