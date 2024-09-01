package com.bzq.matrixmall.exception;

import com.bzq.matrixmall.common.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bzq.matrixmall.common.result.Result;

//全局系统异常处理器
//调整异常处理的HTTP状态码，丰富异常处理类型

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //MissingServletRequestParameterException
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public <T> Result<T> processException(MissingServletRequestParameterException e) {
        log.error(e.getMessage(), e);
        return Result.failed(ResultCode.PARAM_IS_NULL);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public <T> Result<T> handleException(Exception e) throws Exception{
        // 将 Spring Security 异常继续抛出，以便交给自定义处理器处理
        if (e instanceof AccessDeniedException
                || e instanceof AuthenticationException) {
            throw e;
        }
        log.error("unknown exception: {}", e.getMessage());
        e.printStackTrace();
        return Result.failed(e.getLocalizedMessage());
    }

}
