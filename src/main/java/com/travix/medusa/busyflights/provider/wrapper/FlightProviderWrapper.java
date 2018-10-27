package com.travix.medusa.busyflights.provider.wrapper;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;

public interface FlightProviderWrapper<I, O> {
    I convertRequest(BusyFlightsRequest request);
    BusyFlightsResponse convertResponse(O response);
}
