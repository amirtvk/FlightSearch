package com.travix.medusa.busyflights.provider.calculation;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import org.springframework.stereotype.Component;

/**
 * Created by amir on 5/9/19.
 */
@Component("crazyAirFareCalculationStrategy")
public class CrazyAirFareCalculationStrategy implements FareCalculationStrategy<CrazyAirResponse> {
    @Override
    public FareCalculationResult calculate(CrazyAirResponse response) {
        FareCalculationResult fare = new FareCalculationResult();
        fare.setFinalPrice(response.getPrice());
        return fare;
    }
}
