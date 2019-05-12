package com.travix.medusa.busyflights.controller.validation;

import com.travix.medusa.busyflights.BaseTest;
import com.travix.medusa.busyflights.TestUtil;
import com.travix.medusa.busyflights.controller.SearchFlightController;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.service.SearchFlightService;
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

@RunWith(SpringRunner.class)
@WebMvcTest(SearchFlightController.class)
@ComponentScan(basePackages = "com.travix.medusa.busyflights.controller")
public class NoOfPassengersValidationTest extends BaseTest{

    @Autowired
    private MockMvc mvc;

    @MockBean
    SearchFlightController searchFlightController;

    @Test
    public void departureDateShouldBeInRange() throws Exception {
        BusyFlightsRequest request = TestUtil.getRandomBusyFlightsRequest();
        given(searchFlightController.searchFlights(request)).willReturn(new ArrayList<>());

        mvc.perform(get(SERVICE_URL)
                .param(ORIGIN_PARAM, VALID_ORIGIN_VALUE)
                .param(DESTINATION_PARAM, VALID_DESTINATION_VALUE)
                .param(DEPARTURE_PARAM, VALID_DEPARTURE_DATE)
                .param(RETURN_PARAM, VALID_RETURN_DATE)
                .param(NUMBER_OF_PASSENGERS_PARAM, "0"))
                .andExpect(status().is4xxClientError());

        mvc.perform(get(SERVICE_URL)
                .param(ORIGIN_PARAM, VALID_ORIGIN_VALUE)
                .param(DESTINATION_PARAM, VALID_DESTINATION_VALUE)
                .param(DEPARTURE_PARAM, VALID_DEPARTURE_DATE)
                .param(RETURN_PARAM, VALID_RETURN_DATE)
                .param(NUMBER_OF_PASSENGERS_PARAM, "-1"))
                .andExpect(status().is4xxClientError());

        mvc.perform(get(SERVICE_URL)
                .param(ORIGIN_PARAM, VALID_ORIGIN_VALUE)
                .param(DESTINATION_PARAM, VALID_DESTINATION_VALUE)
                .param(DEPARTURE_PARAM, VALID_DEPARTURE_DATE)
                .param(RETURN_PARAM, VALID_RETURN_DATE)
                .param(NUMBER_OF_PASSENGERS_PARAM, "5"))
                .andExpect(status().is4xxClientError());

        mvc.perform(get(SERVICE_URL)
                .param(ORIGIN_PARAM, VALID_ORIGIN_VALUE)
                .param(DESTINATION_PARAM, VALID_DESTINATION_VALUE)
                .param(DEPARTURE_PARAM, VALID_DEPARTURE_DATE)
                .param(RETURN_PARAM, VALID_RETURN_DATE)
                .param(NUMBER_OF_PASSENGERS_PARAM, "1"))
                .andExpect(status().is2xxSuccessful());

        mvc.perform(get(SERVICE_URL)
                .param(ORIGIN_PARAM, VALID_ORIGIN_VALUE)
                .param(DESTINATION_PARAM, VALID_DESTINATION_VALUE)
                .param(DEPARTURE_PARAM, VALID_DEPARTURE_DATE)
                .param(RETURN_PARAM, VALID_RETURN_DATE)
                .param(NUMBER_OF_PASSENGERS_PARAM, "4"))
                .andExpect(status().is2xxSuccessful());

    }

}
