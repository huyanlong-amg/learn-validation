package com.muge.learnvalidation.controller;

import com.muge.learnvalidation.utils.ResultUtil;
import com.muge.learnvalidation.vo.ResponseVO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一处理异常，此处只是对接收得到的bean内部注解校验的异常的拦截处理
 *
 * @Author huyanlong
 * @Date 2022/1/8 3:36
 */
@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseVO handleValidException(MethodArgumentNotValidException e) {
        return ResultUtil.resultError(HttpStatus.BAD_REQUEST.value(), e.getBindingResult().getFieldError().getDefaultMessage());
    }
}
