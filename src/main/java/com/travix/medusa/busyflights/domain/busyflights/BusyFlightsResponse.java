package com.travix.medusa.busyflights.domain.busyflights;

import lombok.Data;

@Data
public class BusyFlightsResponse {
    private String airline;
    private String supplier;
    private double fare;
    private String departureAirportCode;
    private String destinationAirportCode;
    private String departureDate;
    private String arrivalDate;

}
