package com.travix.medusa.busyflights.controller.validation;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IataValidator implements ConstraintValidator<IataFormat, String> {

    @Autowired
    IataCache cache;


    @Override
    public void initialize(IataFormat iataValue) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null || value.isEmpty())
            return false;
        return cache.isValidIata(value);

    }
}
