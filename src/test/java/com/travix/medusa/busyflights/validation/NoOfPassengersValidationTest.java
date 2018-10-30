package com.travix.medusa.busyflights.validation;

import com.travix.medusa.busyflights.TestUtil;
import com.travix.medusa.busyflights.controller.SearchFlightController;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.service.SearchFlightService;
import com.travix.medusa.busyflights.validation.IataCache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {IataCache.class})
@RunWith(SpringRunner.class)
@WebMvcTest(SearchFlightController.class)
@ComponentScan(basePackages = "com.travix.medusa.busyflights.controller")
public class NoOfPassengersValidationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    SearchFlightService searchFlightService;


    public static final String SERVICE_URL = "/searchFlights";
    public static final String ORIGIN_PARAM = "origin";
    public static final String DESTINATION_PARAM = "destination";
    public static final String DEPARTURE_PARAM = "departureDate";
    public static final String RETURN_PARAM = "returnDate";
    public static final String NUMBER_OF_PASSENGERS = "numberOfPassengers";
    public static final String validOriginValue = "AAA";
    public static final String validDestinationValue = "AAA";
    public static final String validDepartureValue = "2011-12-03T10:15:30";
    public static final String validReturnDate = "2011-12-09T11:20:30";

    @Test
    public void departureDateShouldBeInRange() throws Exception {
        BusyFlightsRequest request = TestUtil.getRandomBusyFlightsRequest();
        given(searchFlightService.search(request)).willReturn(new ArrayList<>());

        mvc.perform(get(SERVICE_URL)
                .param(ORIGIN_PARAM, validOriginValue)
                .param(DESTINATION_PARAM, validDestinationValue)
                .param(DEPARTURE_PARAM, validDepartureValue)
                .param(RETURN_PARAM, validReturnDate)
                .param(NUMBER_OF_PASSENGERS, "0"))
                .andExpect(status().is4xxClientError());

        mvc.perform(get(SERVICE_URL)
                .param(ORIGIN_PARAM, validOriginValue)
                .param(DESTINATION_PARAM, validDestinationValue)
                .param(DEPARTURE_PARAM, validDepartureValue)
                .param(RETURN_PARAM, validReturnDate)
                .param(NUMBER_OF_PASSENGERS, "-1"))
                .andExpect(status().is4xxClientError());

        mvc.perform(get(SERVICE_URL)
                .param(ORIGIN_PARAM, validOriginValue)
                .param(DESTINATION_PARAM, validDestinationValue)
                .param(DEPARTURE_PARAM, validDepartureValue)
                .param(RETURN_PARAM, validReturnDate)
                .param(NUMBER_OF_PASSENGERS, "5"))
                .andExpect(status().is4xxClientError());

        mvc.perform(get(SERVICE_URL)
                .param(ORIGIN_PARAM, validOriginValue)
                .param(DESTINATION_PARAM, validDestinationValue)
                .param(DEPARTURE_PARAM, validDepartureValue)
                .param(RETURN_PARAM, validReturnDate)
                .param(NUMBER_OF_PASSENGERS, "1"))
                .andExpect(status().is2xxSuccessful());

        mvc.perform(get(SERVICE_URL)
                .param(ORIGIN_PARAM, validOriginValue)
                .param(DESTINATION_PARAM, validDestinationValue)
                .param(DEPARTURE_PARAM, validDepartureValue)
                .param(RETURN_PARAM, validReturnDate)
                .param(NUMBER_OF_PASSENGERS, "4"))
                .andExpect(status().is2xxSuccessful());

    }

}
