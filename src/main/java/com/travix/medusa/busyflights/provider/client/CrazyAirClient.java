package com.travix.medusa.busyflights.provider.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component("crazyAirClient")
public class CrazyAirClient implements FlightProviderClient<CrazyAirRequest,CrazyAirResponse> {

    @Autowired
    RestTemplate restTemplate;

    @Value("${clients.crazyair.url:http://www.crazy.air/search}")
    private String url;


    @Override
    public List<CrazyAirResponse> searchFlights(CrazyAirRequest request) {

        URI uri = UriComponentsBuilder
                .fromHttpUrl(url)
                .queryParams(RestClientUtil.convertEntityToQueryParam(request))
                .build().toUri();

        ResponseEntity<CrazyAirResponse[]> result
                = restTemplate.getForEntity(uri, CrazyAirResponse[].class);

        return Arrays.asList(result.getBody());

//        return Arrays.asList(CrazyAirResponse.builder()
//                .airline("Crazy")
//                .price(200.12345)
//                .arrivalDate("2011-12-03T10:15:30")
//                .departureDate("2011-12-03T10:15:30")
//                .destinationAirportCode("AAZ")
//                .departureAirportCode("ZZA")
//                .build());
//        return null;
    }
}
