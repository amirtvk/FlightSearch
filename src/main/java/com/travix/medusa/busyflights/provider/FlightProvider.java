package com.travix.medusa.busyflights.provider;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;

import java.util.List;

/**
 * Created by amir on 5/10/19.
 */
public interface FlightProvider {
    List<BusyFlightsResponse> searchFlights(BusyFlightsRequest busyFlightsRequest);
}
