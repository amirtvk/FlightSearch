package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.service.SearchFlightService;
import com.travix.medusa.busyflights.service.SortComparatorGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.print.resources.serviceui;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchFlightController {

    @Autowired
    SearchFlightService searchFlightService;

    @RequestMapping(value = "/searchFlights", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public List<BusyFlightsResponse> searchAirFare(@Valid BusyFlightsRequest request) {

        return searchFlightService.search(request);

    }
}
