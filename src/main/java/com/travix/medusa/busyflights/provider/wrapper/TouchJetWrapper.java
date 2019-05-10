package com.travix.medusa.busyflights.provider.wrapper;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;
import static java.time.format.DateTimeFormatter.ISO_INSTANT;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

/**
 * Created by amir on 5/9/19.
 */
@Component("toughJetWrapper")
public class TouchJetWrapper implements FlightProviderWrapper<ToughJetRequest,ToughJetResponse>{
    @Override
    public ToughJetRequest convertRequest(BusyFlightsRequest request) {
        return ToughJetRequest.builder()
                .numberOfAdults(request.getNumberOfPassengers())
                .inboundDate(request.getDepartureDate())
                .outboundDate(request.getReturnDate())
                .from(request.getOrigin())
                .to(request.getDestination())
                .build();
    }

    @Override
    public BusyFlightsResponse convertResponse(ToughJetResponse response) {
        return BusyFlightsResponse.builder()
                        .departureAirportCode(response.getDepartureAirportName())
                        .destinationAirportCode(response.getArrivalAirportName())
                        .airline(response.getCarrier())

                        .departureDate(convertFormat(
                                response.getInboundDateTime(),
                                ISO_INSTANT.withZone(ZoneId.systemDefault()),
                                ISO_LOCAL_DATE_TIME))

                        .arrivalDate(convertFormat(
                                response.getOutboundDateTime(),
                                ISO_INSTANT.withZone(ZoneId.systemDefault()),
                                ISO_LOCAL_DATE_TIME))

                        .build();
    }


    public static String convertFormat(String date, DateTimeFormatter from, DateTimeFormatter to) {
        return LocalDateTime.parse(date, from).format(to);
    }
}
