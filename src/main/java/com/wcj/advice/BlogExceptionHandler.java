package com.wcj.advice;

import com.wcj.exception.BlogException;
import com.wcj.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 自定义异常处理
 *
 * @author wcj
 * @Date 2020/3/23 13:23
 * @Version 1.0
 */
@ControllerAdvice
@Slf4j
public class BlogExceptionHandler {

    /**
     * 对全局自定义Exception进行处理
     * @param exception
     */
    @ExceptionHandler(BlogException.class)
    @ResponseBody
    public Result<Object> exceptionHandler(BlogException exception) {
        log.error("统一异常处理,",exception);
        return new Result<>(exception.getErrorCode(), exception.getMessage());
    }
}
