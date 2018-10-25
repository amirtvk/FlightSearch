package com.travix.medusa.busyflights.domain.crazyair;

import lombok.Data;

@Data
public class CrazyAirResponse {

    private String airline;
    private double price;
    private String cabinclass;
    private String departureAirportCode;
    private String destinationAirportCode;
    private String departureDate;
    private String arrivalDate;
}
