package com.travix.medusa.busyflights.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashMap;

public class IataValidator implements ConstraintValidator<IataValue, String> {

    HashMap<String,Boolean> IATA_Cache;

    @Override
    public void initialize(IataValue iataValue) {
        //TODO : Initialize Catch of IATA Values from DB
        IATA_Cache = new HashMap<>();
        IATA_Cache.put("PWA", true);
        IATA_Cache.put("AAZ", true);
        IATA_Cache.put("APT", true);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null || value.isEmpty())
            return false;
        return (IATA_Cache.get(value) == true) ? true : false;
    }
}
