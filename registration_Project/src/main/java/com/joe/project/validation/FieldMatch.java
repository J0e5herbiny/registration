package com.joe.project.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = FieldMatchValidator.class)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,
         ElementType.ANNOTATION_TYPE})
public @interface FieldMatch {

    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String first();
    String second();

    @Documented
    @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List{
        FieldMatch[] value();
    }

}
