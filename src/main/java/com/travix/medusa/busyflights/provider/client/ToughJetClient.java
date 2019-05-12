package com.travix.medusa.busyflights.provider.client;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Component("toughJetClient")
public class ToughJetClient implements FlightProviderClient<ToughJetRequest, ToughJetResponse> {


    @Autowired
    RestTemplate restTemplate;

    @Value("${clients.toughjet.url:http://www.tough-jet.air/search}")
    private String url;

    @Override
    public List<ToughJetResponse> searchFlights(ToughJetRequest request) {

        URI uri = UriComponentsBuilder
                .fromHttpUrl(url)
                .queryParams(RestClientUtil.convertEntityToQueryParam(request))
                .build().toUri();

        ResponseEntity<ToughJetResponse[]> result
                = restTemplate.getForEntity(uri, ToughJetResponse[].class);

        return Arrays.asList(result.getBody());

//        return Arrays.asList(ToughJetResponse.builder()
//                .basePrice(180)
//                .tax(10.987654)
//                .discount(50)
//                .arrivalAirportName("AAZ")
//                .departureAirportName("ASD")
//                .inboundDateTime("2014-09-02T08:05:23.653Z")
//                .outboundDateTime("2014-09-02T08:05:23.653Z")
//                .build());
    }
}
