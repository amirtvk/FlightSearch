package com.travix.medusa.busyflights.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.travix.medusa.busyflights.TestUtil;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.service.SearchFlightService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@ContextConfiguration(classes = {IataCache.class, SearchFlightServiceImpl.class})
@RunWith(SpringRunner.class)
//@ComponentScan(basePackages = "com.travix.medusa.busyflights")
//@SpringBootTest
@WebMvcTest(SearchFlightController.class)
public class controllerTest {


    @Autowired
    private MockMvc mockMvc;

//    @Before
//    public void setUp() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//    }

    @MockBean
    SearchFlightService searchFlightService;


    public static final String SERVICE_URL = "/searchFlights";
    public static final String ORIGIN_PARAM = "origin";
    public static final String DESTINATION_PARAM = "destination";
    public static final String DEPARTURE_PARAM = "departureDate";
    public static final String RETURN_PARAM = "returnDate";
    public static final String NUMBER_OF_PASSENGERS_PARAM = "numberOfPassengers";
    public static final String SORT_PARAM = "sort";
    public static final String PROJECTION_PARAM = "projection";

    public static final String validOriginValue = "AAA";
    public static final String validDestinationValue = "AAA";
    public static final String validDepartureDate = "2011-12-03T10:15:30";
    public static final String validReturnDate = "2011-12-09T11:20:30";
    public static final String validNumberOfPassengers = "1";
    public static final String validSortValue = "fare,asc";
    public static final String validProjectionValue = "noProjection";


    BusyFlightsResponse response;
    BusyFlightsRequest request;
    @Before
    public void init(){
        request = TestUtil.getRandomBusyFlightsRequest();
        response = TestUtil.getRandomBusyFlightsResponse(request);
    }

    @Test
    public void controllerShouldReturnServiceResult() throws Exception {

        request.setDepartureDate(validDepartureDate);
        request.setReturnDate(validReturnDate);
        request.setDestination(validDestinationValue);
        request.setOrigin(validOriginValue);
        request.setNumberOfPassengers(Integer.valueOf(validNumberOfPassengers));
        request.setSort(validSortValue);
        request.setProjection(validProjectionValue);

        given(searchFlightService.search(request)).willReturn(Arrays.asList(response));

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        mockMvc.perform(get(SERVICE_URL)
                .param(ORIGIN_PARAM, validOriginValue)
                .param(DESTINATION_PARAM, validDestinationValue)
                .param(DEPARTURE_PARAM, validDepartureDate)
                .param(RETURN_PARAM, validReturnDate)
                .param(NUMBER_OF_PASSENGERS_PARAM, validNumberOfPassengers)
                .param(SORT_PARAM, validSortValue)
                .param(PROJECTION_PARAM, validProjectionValue))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(ow.writeValueAsString(Arrays.asList(response))))
        ;
    }

}
