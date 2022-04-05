package com.muge.learnvalidation.controller;

import com.muge.learnvalidation.utils.ResultUtil;
import com.muge.learnvalidation.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * 统一处理异常
 *
 * @Author huyanlong
 * @Date 2022/1/8 3:36
 */
@RestControllerAdvice
@Slf4j
public class AdviceController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseVO handleValidException(MethodArgumentNotValidException e) {
        return ResultUtil.resultError(HttpStatus.BAD_REQUEST.value(), e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseVO handleConstraintViolationException(ConstraintViolationException ex) {
        return ResultUtil.resultError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }
}
