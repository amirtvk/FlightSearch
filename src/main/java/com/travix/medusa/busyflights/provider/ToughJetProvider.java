package com.travix.medusa.busyflights.provider;

import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.provider.calculation.FareCalculationStrategy;
import com.travix.medusa.busyflights.provider.client.FlightProviderClient;
import com.travix.medusa.busyflights.provider.wrapper.FlightProviderWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by amir on 5/9/19.
 */
@Component
public class ToughJetProvider implements FlightProvider<ToughJetRequest, ToughJetResponse>{

    @Autowired
    @Qualifier("toughJetClient")
    FlightProviderClient flightProviderClient;

    @Autowired
    @Qualifier("toughJetWrapper")
    FlightProviderWrapper flightProviderWrapper;

    @Autowired
    @Qualifier("toughJetFareCalculationStrategy")
    FareCalculationStrategy fareCalculationStrategy;

    @Override
    public String getProviderName() {
        return "ToughJet";
    }

    @Override
    public FlightProviderClient<ToughJetRequest, ToughJetResponse> getClient() {
        return flightProviderClient;
    }

    @Override
    public FlightProviderWrapper<ToughJetRequest, ToughJetResponse> getReqRespWrapper() {
        return flightProviderWrapper;
    }

    @Override
    public FareCalculationStrategy<ToughJetResponse> getFareCalculationStrategy() {
        return fareCalculationStrategy;
    }
}
