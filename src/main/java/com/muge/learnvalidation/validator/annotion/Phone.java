package com.muge.learnvalidation.validator.annotion;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Author huyanlong
 * @Date 2022/1/8 1:29
 */
//@ConstraintComposition(CompositionType.OR)
@Pattern(regexp = "1[3|4|5|7|8][0-9]\\d{8}")
@NotBlank
@Documented
@Constraint(validatedBy = {})
@Target({METHOD, FIELD, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@ReportAsSingleViolation
public @interface Phone {
    String message() default "手机号校验错误";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}