package com.travix.medusa.busyflights.provider.wrapper;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;

/**
 * Created by amir on 5/9/19.
 */
public class CrazyAirWrapper implements FlightProviderWrapper<CrazyAirRequest,CrazyAirResponse>{
    @Override
    public CrazyAirRequest convertRequest(BusyFlightsRequest request) {
        return null;
    }

    @Override
    public BusyFlightsResponse convertResponse(CrazyAirResponse response) {
        return null;
    }
}
