package com.travix.medusa.busyflights.provider.client;

import java.util.List;

public interface FlightProviderClient<I,O> {
    List<O> searchFlights(I request);
}
