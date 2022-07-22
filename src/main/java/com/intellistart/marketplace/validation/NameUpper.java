package com.intellistart.marketplace.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author @bkalika
 * Created on 22.07.2022 2:34 PM
 * https://www.baeldung.com/spring-mvc-custom-validator
 */

@Documented
@Constraint(validatedBy = NameConstrainValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NameUpper {
    String message() default "Name must be started in Capital letter!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
