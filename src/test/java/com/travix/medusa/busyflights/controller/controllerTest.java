package com.travix.medusa.busyflights.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.travix.medusa.busyflights.TestUtil;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.service.SearchFlightService;
import com.travix.medusa.busyflights.service.SearchFlightServiceImpl;
import com.travix.medusa.busyflights.validation.IataCache;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@ContextConfiguration(classes = {IataCache.class, SearchFlightServiceImpl.class})
@RunWith(SpringRunner.class)
//@ComponentScan(basePackages = "com.travix.medusa.busyflights.controller")
@SpringBootTest
public class controllerTest {


    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

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
    public static final String validNumberOfPassengers = "1";


    @Test
    public void controllerShouldReturnServiceResult() throws Exception {
        BusyFlightsRequest request = TestUtil.getRandomBusyFlightsRequest();
        BusyFlightsResponse response = TestUtil.getRandomBusyFlightsResponse(request);
        given(searchFlightService.search(request)).willReturn(Arrays.asList(response));

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        mockMvc.perform(get(SERVICE_URL)
                .param(ORIGIN_PARAM, validOriginValue)
                .param(DESTINATION_PARAM, validDestinationValue)
                .param(DEPARTURE_PARAM, validDepartureValue)
                .param(RETURN_PARAM, validReturnDate)
                .param(NUMBER_OF_PASSENGERS, validNumberOfPassengers))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(ow.writeValueAsString(response)))
        ;
    }

}
