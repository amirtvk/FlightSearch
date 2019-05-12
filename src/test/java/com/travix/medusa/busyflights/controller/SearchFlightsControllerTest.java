package com.travix.medusa.busyflights.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.travix.medusa.busyflights.BaseTest;
import com.travix.medusa.busyflights.TestUtil;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.service.SearchFlightService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(SearchFlightController.class)
@ComponentScan(basePackages = "com.travix.medusa.busyflights.controller")
public class SearchFlightsControllerTest extends BaseTest{

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    SearchFlightService searchFlightService;

    @Test
    public void controllerShouldReturnServiceResult() throws Exception {

        BusyFlightsRequest request = new BusyFlightsRequest();
        request.setDepartureDate(VALID_DEPARTURE_DATE);
        request.setReturnDate(VALID_RETURN_DATE);
        request.setDestination(VALID_DESTINATION_VALUE);
        request.setOrigin(VALID_ORIGIN_VALUE);
        request.setNumberOfPassengers(Integer.valueOf(VALID_NUMBER_OF_PASSENGERS));
        request.setSort(VALID_SORT_VALUE);
        request.setProjection(VALID_PROJECTION_VALUE);

        BusyFlightsResponse response = TestUtil.getRandomBusyFlightsResponse(request);

        given(searchFlightService.search(request)).willReturn(Arrays.asList(response));
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        mockMvc.perform(get(SERVICE_URL)
                .param(ORIGIN_PARAM, VALID_ORIGIN_VALUE)
                .param(DESTINATION_PARAM, VALID_DESTINATION_VALUE)
                .param(DEPARTURE_PARAM, VALID_DEPARTURE_DATE)
                .param(RETURN_PARAM, VALID_RETURN_DATE)
                .param(NUMBER_OF_PASSENGERS_PARAM, VALID_NUMBER_OF_PASSENGERS)
                .param(SORT_PARAM, VALID_SORT_VALUE)
                .param(PROJECTION_PARAM, VALID_PROJECTION_VALUE))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(ow.writeValueAsString(Arrays.asList(response))));
    }

}
