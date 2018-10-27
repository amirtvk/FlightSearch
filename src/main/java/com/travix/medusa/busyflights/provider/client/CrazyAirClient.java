package com.travix.medusa.busyflights.provider.client;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;

import java.util.List;

public class CrazyAirClient implements FlightProviderClient<CrazyAirRequest,CrazyAirResponse> {
    @Override
    public List<CrazyAirResponse> searchFlights(CrazyAirRequest request) {
        return null;
    }
}
