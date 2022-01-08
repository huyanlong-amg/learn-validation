package com.muge.learnvalidation.utils;

import com.muge.learnvalidation.vo.ResponseVO;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;

/**
 * @Author huyanlong
 * @Date 2022/1/8 1:42
 */
public final class ResultUtil {
    public static <T> ResponseVO<T> resultSuccess(T data) {
        return resultSuccess(HttpStatus.OK.value(), data);
    }

    public static <T> ResponseVO<T> resultSuccess(int code, T data) {
        return resultSuccess(code, Strings.EMPTY, data);
    }

    public static <T> ResponseVO<T> resultSuccess(int code, String message, T data) {
        return new ResponseVO<T>(code, message, data);
    }

    public static <T> ResponseVO<T> resultError(String message) {
        return resultError(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }

    public static <T> ResponseVO<T> resultError(int code, String message) {
        return (ResponseVO<T>) resultError(code, message, Strings.EMPTY);
    }

    public static <T> ResponseVO<T> resultError(int code, String message, T data) {
        return new ResponseVO<T>(code, message, data);
    }
}
