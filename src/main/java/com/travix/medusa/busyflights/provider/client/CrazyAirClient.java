package com.travix.medusa.busyflights.provider.client;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("crazyAirClient")
public class CrazyAirClient implements FlightProviderClient<CrazyAirRequest,CrazyAirResponse> {
    @Override
    public List<CrazyAirResponse> searchFlights(CrazyAirRequest request) {
        return null;
    }
}
