package com.travix.medusa.busyflights.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.format.DateTimeFormatter;

public class TimeValidator implements ConstraintValidator<TimeFormat, String> {

    private DateTimeFormatter format;

    @Override
    public void initialize(TimeFormat timeFormat) {
        format = DateTimeFormatter.ISO_LOCAL_DATE;
    }
    
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        try {
            format.parse(value);
            return true;
        } catch (Throwable t) {
            return false;
        }
    }
}
