package com.intellistart.marketplace.validation;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author @bkalika
 * Created on 22.07.2022 2:35 PM
 * https://www.baeldung.com/spring-mvc-custom-validator
 */

@Component
public class NameConstrainValidator implements ConstraintValidator<NameUpper, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Character.isUpperCase(value.charAt(0));
    }
}
