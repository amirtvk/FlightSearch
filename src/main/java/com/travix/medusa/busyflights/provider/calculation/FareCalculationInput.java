package com.travix.medusa.busyflights.provider.calculation;

import lombok.Data;

@Data
public class FareCalculationInput {
    private double basePrice;
    private double finalPrice;
    private double tax;
    private double discount;
}
