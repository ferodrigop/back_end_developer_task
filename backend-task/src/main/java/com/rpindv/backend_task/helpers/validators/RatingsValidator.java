package com.rpindv.backend_task.helpers.validators;

import com.rpindv.backend_task.helpers.validators.custom_anotations.RatingsValue;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RatingsValidator implements ConstraintValidator<RatingsValue, Double> {
    @Override
    public void initialize(RatingsValue constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext constraintValidatorContext) {
        return (value >= 1.0 && value <= 5.0 && (value * 10) % 5 == 0);
    }
}
