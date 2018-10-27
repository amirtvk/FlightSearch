package com.travix.medusa.busyflights.provider;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.provider.calculation.FareCalculationStrategy;
import com.travix.medusa.busyflights.provider.client.FlightProviderClient;
import com.travix.medusa.busyflights.provider.wrapper.FlightProviderWrapper;

import java.util.ArrayList;
import java.util.List;

public interface FlightProvider {

    FlightProviderClient getClient();

    FlightProviderWrapper getReqRespWrapper();

    FareCalculationStrategy getFareCalculationStrategy();

    default List<BusyFlightsResponse> searchFilghts(BusyFlightsRequest busyFlightsRequest){
        return new ArrayList<>();
    }
}
