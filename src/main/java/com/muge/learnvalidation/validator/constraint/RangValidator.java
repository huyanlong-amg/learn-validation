package com.muge.learnvalidation.validator.constraint;


import com.muge.learnvalidation.validator.annotion.Range;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 字符串范围约束，限制长度【校验器】
 *
 * @Author huyanlong
 * @Date 2022/1/8 0:50
 */
public class RangValidator implements ConstraintValidator<Range, String> {
    private long min;
    private long max;

    @Override
    public void initialize(Range constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (null == value || StringUtils.isBlank(value)) {
            return min == 0;
        }
        return value.length() >= min && value.length() <= max;
    }
}
