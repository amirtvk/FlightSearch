package com.travix.medusa.busyflights;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class TestUtil {
    public static BusyFlightsResponse getRandomBusyFlightsResponse(BusyFlightsRequest request){
        return BusyFlightsResponse.builder()
                .departureDate(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .arrivalDate(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .fare(new Random().nextDouble())
                .supplier("CrazyAir")
                .airline("ABC")
                .departureAirportCode(request.getOrigin())
                .destinationAirportCode(request.getDestination())
                .build();
    }
    public static BusyFlightsRequest getRandomBusyFlightsRequest() {
        return BusyFlightsRequest
                .builder()
                .numberOfPassengers(3)
                .departureDate(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .returnDate(LocalDateTime.now().plusDays(4).format(DateTimeFormatter.ISO_LOCAL_DATE))
                .origin("AAZ")
                .destination("AAP")
                .sort("fare,asc")
                .build();
    }

}
