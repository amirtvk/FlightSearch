package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.service.SearchFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
public class SearchFlightController {

    @Autowired
    SearchFlightService searchFlightService;

    @RequestMapping(value = "/flights/search", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public List<BusyFlightsResponse> searchFlights(@Valid BusyFlightsRequest request) {
        return searchFlightService.search(request);
    }

    //TODO: Add a Controller to expose auto generated Documents(meta-data) required this service
}
