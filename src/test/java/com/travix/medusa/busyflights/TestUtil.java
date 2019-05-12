package com.travix.medusa.busyflights;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import static java.time.format.DateTimeFormatter.ISO_INSTANT;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

public class TestUtil {
    private static Random random = new Random();
    public static BusyFlightsResponse getRandomBusyFlightsResponse(BusyFlightsRequest request){
        return BusyFlightsResponse.builder()
                .departureDate(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .arrivalDate(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .fare(1500 + random.nextInt(100))
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

    public static CrazyAirRequest getRandomCrazyAirRequest() {
        return CrazyAirRequest
                .builder()
                .departureDate(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .returnDate(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .destination("AAP")
                .origin("AAZ")
                .passengerCount(1).build();
    }

    public static CrazyAirResponse getRandomCrazyAirResponse(){
        return
                CrazyAirResponse
                .builder()
                .airline("airline")
                .arrivalDate(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .departureDate(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .departureAirportCode("AAP")
                .destinationAirportCode("AAZ")
                .price(1500.123)
                .cabinclass("E")
                .build();
    }

    public static ToughJetResponse getRandomToughJetResponse(){

       return ToughJetResponse
                .builder()
                .inboundDateTime(DateTimeFormatter.ISO_INSTANT.format(Instant.now()))
                .outboundDateTime(DateTimeFormatter.ISO_INSTANT.format(Instant.now()))
                .departureAirportName("AAP")
                .arrivalAirportName("AAZ")
                .basePrice(1400)
                .tax(100)
                .discount(2)
                .carrier("airline")
                .build();
    }

}
