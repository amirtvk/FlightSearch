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
public class DepartureDateValidationTest extends BaseTest{

    @Autowired
    private MockMvc mvc;

    @MockBean
    SearchFlightController searchFlightController;

    @Test
    public void departureDateShouldBeValid() throws Exception {
        BusyFlightsRequest request = TestUtil.getRandomBusyFlightsRequest();
        given(searchFlightController.searchFlights(request)).willReturn(new ArrayList<>());

        mvc.perform(get(SERVICE_URL)
                .param(ORIGIN_PARAM, VALID_ORIGIN_VALUE)
                .param(DESTINATION_PARAM, VALID_DESTINATION_VALUE)
                .param(DEPARTURE_PARAM, "2011-12-03T10:15:3")
                .param(RETURN_PARAM, VALID_RETURN_DATE)
                .param(NUMBER_OF_PASSENGERS_PARAM, VALID_NUMBER_OF_PASSENGERS))
                .andExpect(status().is4xxClientError());

        mvc.perform(get(SERVICE_URL)
                .param(ORIGIN_PARAM, VALID_ORIGIN_VALUE)
                .param(DESTINATION_PARAM, VALID_DESTINATION_VALUE)
                .param(DEPARTURE_PARAM, "2011-12-03")
                .param(RETURN_PARAM, VALID_RETURN_DATE)
                .param(NUMBER_OF_PASSENGERS_PARAM, VALID_NUMBER_OF_PASSENGERS))
                .andExpect(status().is4xxClientError());

        mvc.perform(get(SERVICE_URL)
                .param(ORIGIN_PARAM, VALID_ORIGIN_VALUE)
                .param(DESTINATION_PARAM, VALID_DESTINATION_VALUE)
                .param(DEPARTURE_PARAM, "2011-12-03T10:15:30")
                .param(RETURN_PARAM, VALID_RETURN_DATE)
                .param(NUMBER_OF_PASSENGERS_PARAM, VALID_NUMBER_OF_PASSENGERS))
                .andExpect(status().is2xxSuccessful());
    }

}
