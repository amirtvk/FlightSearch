package com.travix.medusa.busyflights.provider.calculation;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import org.springframework.stereotype.Component;

/**
 * Created by amir on 5/9/19.
 */
@Component("toughJetFareCalculationStrategy")
public class ToughJetFareCalculationStrategy implements FareCalculationStrategy<ToughJetResponse> {
    @Override
    public FareCalculationResult calculate(ToughJetResponse response) {
        FareCalculationResult fare = new FareCalculationResult();
        fare.setFinalPrice(response.getBasePrice() * (1 - (response.getDiscount() / 100)) + response.getTax() );
        return fare;
    }
}
