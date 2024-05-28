package com.rpindv.backend_task.helpers.validators.custom_anotations;

import jakarta.validation.Constraint;
import com.rpindv.backend_task.helpers.validators.RatingsValidator;
import jakarta.validation.ReportAsSingleViolation;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RatingsValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
public @interface RatingsValue {

    String message() default "Invalid Rating value. Valid values are: 1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}