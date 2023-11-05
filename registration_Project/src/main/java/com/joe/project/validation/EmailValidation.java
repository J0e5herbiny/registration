package com.joe.project.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(
        {ElementType.TYPE,
        ElementType.FIELD,
        ElementType.ANNOTATION_TYPE}
)
@Constraint(validatedBy = EmailValidator.class)
public @interface EmailValidation {

    String message() default "Invalid email";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
