package com.travix.medusa.busyflights.provider.client;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component("toughJetClient")
public class ToughJetClient implements FlightProviderClient<ToughJetRequest, ToughJetResponse> {
    @Override
    public List<ToughJetResponse> searchFlights(ToughJetRequest request) {
        return Arrays.asList(ToughJetResponse.builder()
                .basePrice(180)
                .tax(10.987654)
                .discount(50)
                .arrivalAirportName("AAZ")
                .departureAirportName("ASD")
                .inboundDateTime("2014-09-02T08:05:23.653Z")
                .outboundDateTime("2014-09-02T08:05:23.653Z")
                .build());
    }
}
