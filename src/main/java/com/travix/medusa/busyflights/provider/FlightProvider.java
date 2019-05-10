package com.travix.medusa.busyflights.provider;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.provider.calculation.FareCalculationStrategy;
import com.travix.medusa.busyflights.provider.client.FlightProviderClient;
import com.travix.medusa.busyflights.provider.wrapper.FlightProviderWrapper;

import java.util.ArrayList;
import java.util.List;

public interface FlightProvider<I, O>  extends FlightProviderInterface {

    String getProviderName();

    FlightProviderClient<I, O> getClient();

    FlightProviderWrapper<I, O> getReqRespWrapper();

    FareCalculationStrategy<O> getFareCalculationStrategy();

    default List<BusyFlightsResponse> searchFilghts(BusyFlightsRequest busyFlightsRequest){

        I request = getReqRespWrapper().convertRequest(busyFlightsRequest);
        List<O> clientResponse = getClient().searchFlights(request);
        List<BusyFlightsResponse> result = new ArrayList<>();

        for(O item : clientResponse){
            BusyFlightsResponse busyFlightResponse = getReqRespWrapper().convertResponse(item);
            busyFlightResponse.setSupplier(getProviderName());
            busyFlightResponse.setFare(getFareCalculationStrategy().calculate(item).getFinalPrice());
            result.add(busyFlightResponse);
        }
        return result;
    }
}
