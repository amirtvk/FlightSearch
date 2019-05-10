package com.travix.medusa.busyflights.provider.client;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component("crazyAirClient")
public class CrazyAirClient implements FlightProviderClient<CrazyAirRequest,CrazyAirResponse> {
    @Override
    public List<CrazyAirResponse> searchFlights(CrazyAirRequest request) {
        return Arrays.asList(CrazyAirResponse.builder()
                .airline("Crazy")
                .price(200.12345)
                .arrivalDate("2011-12-03T10:15:30")
                .departureDate("2011-12-03T10:15:30")
                .destinationAirportCode("AAZ")
                .departureAirportCode("ZZA")
                .build());
//        return null;
    }
}
