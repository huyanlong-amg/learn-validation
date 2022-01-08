package com.muge.learnvalidation.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author huyanlong
 * @Date 2022/1/8 1:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVO<T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;
}
