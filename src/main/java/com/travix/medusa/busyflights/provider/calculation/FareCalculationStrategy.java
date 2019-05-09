package com.travix.medusa.busyflights.provider.calculation;

public interface FareCalculationStrategy<O> {
    FareCalculationResult calculate(O response);
}
