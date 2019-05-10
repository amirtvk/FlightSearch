package com.travix.medusa.busyflights.provider.wrapper;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.*;

/**
 * Created by amir on 5/9/19.
 */

@Component("crazyAirWrapper")
public class CrazyAirWrapper implements FlightProviderWrapper<CrazyAirRequest,CrazyAirResponse>{
    @Override
    public CrazyAirRequest convertRequest(BusyFlightsRequest request) {
        return CrazyAirRequest.builder()
                .passengerCount(request.getNumberOfPassengers())
                .departureDate(request.getDepartureDate())
                .returnDate(request.getReturnDate())
                .origin(request.getOrigin())
                .destination(request.getDestination())
                .build();
    }

    @Override
    public BusyFlightsResponse convertResponse(CrazyAirResponse response) {
       return BusyFlightsResponse.builder()
                .departureAirportCode(response.getDepartureAirportCode())
                .destinationAirportCode(response.getDestinationAirportCode())
                .airline(response.getAirline())
                .departureDate(convertFormat(
                        response.getDepartureDate(),
                        ISO_LOCAL_DATE_TIME.withZone(ZoneId.systemDefault()),
                        ISO_DATE_TIME))
                .arrivalDate(convertFormat(
                        response.getArrivalDate(),
                        ISO_LOCAL_DATE_TIME.withZone(ZoneId.systemDefault()),
                        ISO_DATE_TIME))
               .build();
    }


    public static String convertFormat(String date, DateTimeFormatter from, DateTimeFormatter to) {
        return LocalDateTime.parse(date, from).format(to);
    }
}
