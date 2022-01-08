package com.muge.learnvalidation.vo;

import lombok.Data;
import lombok.experimental.Delegate;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 * 包装 List类型，并声明 @Valid 注解
 *
 * @Author huyanlong
 * @Date 2022/1/8 18:38
 */
@Data
public class ValidationListVO<E> implements List<E> {
    @Delegate
    @NotEmpty(message = "不能为空")
    @Valid
    public List<E> list = new ArrayList<>();
}
