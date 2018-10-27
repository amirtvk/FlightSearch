package com.travix.medusa.busyflights.provider.calculation;

public interface FareCalculationStrategy {
    FareCalculationOutput calculate(FareCalculationInput input);
}
