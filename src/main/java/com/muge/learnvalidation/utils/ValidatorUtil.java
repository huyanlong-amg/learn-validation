package com.muge.learnvalidation.utils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Optional;
import java.util.Set;

/**
 * @Author huyanlong
 * @Date 2022/1/8 16:00
 */
public final class ValidatorUtil {

    /**
     * 检查被校验参数标记的bean是否合法参数是否合法
     *
     * @param object
     * @param groups
     * @return
     */
    public static Optional<String> inValid(Object object, Class<?>... groups) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Object>> validate = validator.validate(object, groups);
        return validate.stream()
                .map(violation -> Optional.of(violation.getMessage()))
                .findFirst()
                .orElse(Optional.empty());
    }
}
