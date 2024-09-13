package com.bzq.matrixmall.exception;

import com.bzq.matrixmall.common.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bzq.matrixmall.common.result.Result;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.stream.Collectors;

//全局系统异常处理器
//调整异常处理的HTTP状态码，丰富异常处理类型

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //处理所有类型为Exception及其子类的异常
    //当控制器方法中抛出Exception类型的异常时，Spring 框架会自动调用这个方法来处理异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public <T> Result<T> handleException(Exception e) throws Exception {
        // 将 Spring Security 异常继续抛出，以便交给自定义处理器处理
        if (e instanceof AccessDeniedException || e instanceof AuthenticationException) {
            throw e;
        }

        log.error("unknown exception:{}:{}", e.getClass().getName(), e.getMessage());
        e.printStackTrace();

        return Result.failed(e.getLocalizedMessage());
    }


    //专门用于处理MissingServletRequestParameterException类型的异常
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public <T> Result<T> processException(MissingServletRequestParameterException e) {
        log.error(e.getMessage(), e);
        return Result.failed(ResultCode.PARAM_IS_NULL);
    }

    //RequestBody参数的校验
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public <T> Result<T> processException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException:{}", e.getMessage());
        String msg = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining("；"));
        return Result.failed(ResultCode.PARAM_ERROR, msg);
    }

    //上传文件超过大小限制
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public <T> Result<T> processException(MaxUploadSizeExceededException e) {
        log.error("MaxUploadSizeExceededException:{}", e.getMessage());
        return Result.failed(ResultCode.UPLOAD_FILE_EXCEED_SIZE_LIMIT);
    }

}
