package com.joe.project.validation;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {


    private String firstFieldName;
    private String secondFieldName;
    private String message;

    @Override
    public void initialize(final FieldMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(final Object object, final ConstraintValidatorContext constraintValidatorContext) {
        boolean valid = true;
        try {
            final Object fObject = new BeanWrapperImpl(object).getPropertyValue(firstFieldName);
            final Object sObject = new BeanWrapperImpl(object).getPropertyValue(secondFieldName);

            valid = fObject == null && sObject == null || fObject != null && fObject.equals(sObject);
        }catch (Exception ignoreE){}

        if (!valid){
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(firstFieldName)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }

        return valid;
    }
}
