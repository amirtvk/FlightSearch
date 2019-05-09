package com.travix.medusa.busyflights.provider;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
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
public class CrazyAirProvider implements FlightProvider<CrazyAirRequest, CrazyAirResponse>{

    @Autowired
    @Qualifier("crazyAirClient")
    FlightProviderClient flightProviderClient;

    @Autowired
    @Qualifier("crazyAirWrapper")
    FlightProviderWrapper flightProviderWrapper;

    @Autowired
    @Qualifier("crazyAirFareCalculationStrategy")
    FareCalculationStrategy fareCalculationStrategy;

    @Override
    public String getProviderName() {
        return "CrazyAir";
    }


    @Override
    public FlightProviderClient<CrazyAirRequest, CrazyAirResponse> getClient() {
        return flightProviderClient;
    }

    @Override
    public FlightProviderWrapper<CrazyAirRequest, CrazyAirResponse> getReqRespWrapper() {
        return flightProviderWrapper;
    }

    @Override
    public FareCalculationStrategy<CrazyAirResponse> getFareCalculationStrategy() {
        return fareCalculationStrategy;
    }
}
