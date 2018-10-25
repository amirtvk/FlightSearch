package com.travix.medusa.busyflights.domain.busyflights;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BusyFlightsRequest {

    @NotNull
    private String origin;
    @NotNull
    private String destination;
    private String departureDate;
    private String returnDate;
    private int numberOfPassengers;
}
