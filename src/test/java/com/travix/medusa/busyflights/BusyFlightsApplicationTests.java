package com.travix.medusa.busyflights;

import com.travix.medusa.busyflights.controller.SearchFlightController;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.provider.client.CrazyAirClient;
import com.travix.medusa.busyflights.provider.client.ToughJetClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SearchFlightController.class)
@ComponentScan(basePackages = "com.travix.medusa.busyflights.")
public class BusyFlightsApplicationTests  extends BaseTest{


	@Autowired
	private MockMvc mockMvc;

	@MockBean
	CrazyAirClient crazyAirClient;

	@MockBean
	ToughJetClient toughJetClient;


	@Test
	public void integrationTest() throws Exception {

		BusyFlightsRequest request = TestUtil.getRandomBusyFlightsRequest();

		CrazyAirResponse crazyAirResponse = TestUtil.getRandomCrazyAirResponse();
		CrazyAirResponse crazyAirResponse2 = TestUtil.getRandomCrazyAirResponse();
		crazyAirResponse.setPrice(1200.1234);
		crazyAirResponse2.setPrice(1300.999);

		ToughJetResponse toughJetResponse = TestUtil.getRandomToughJetResponse();
		toughJetResponse.setBasePrice(2000);
		toughJetResponse.setDiscount(30);
		toughJetResponse.setTax(10.991);

		Mockito.when(crazyAirClient.searchFlights(any())).thenReturn(Arrays.asList(crazyAirResponse, crazyAirResponse2));
		Mockito.when(toughJetClient.searchFlights(any())).thenReturn(Arrays.asList(toughJetResponse));

		mockMvc.perform(get(SERVICE_URL)

				.param(ORIGIN_PARAM, VALID_ORIGIN_VALUE)
				.param(DESTINATION_PARAM, VALID_DESTINATION_VALUE)
				.param(DEPARTURE_PARAM, VALID_DEPARTURE_DATE)
				.param(RETURN_PARAM, VALID_RETURN_DATE)
				.param(NUMBER_OF_PASSENGERS_PARAM, VALID_NUMBER_OF_PASSENGERS)

				.param(SORT_PARAM, SORT_BY_FARE_ASCENDING)
				.param(PROJECTION_PARAM, FARE_ROUND_TWO_DECIMAL_PROJECTION))

				.andExpect(status().is2xxSuccessful())
				.andExpect(jsonPath("$.[0].['fare']").value("1200.12"))
				.andExpect(jsonPath("$.[0].['supplier']").value("CrazyAir"))

				.andExpect(jsonPath("$.[1].['fare']").value("1301.0"))
				.andExpect(jsonPath("$.[1].['supplier']").value("CrazyAir"))

				.andExpect(jsonPath("$.[2].['fare']").value("1410.99"))
				.andExpect(jsonPath("$.[2].['supplier']").value("ToughJet"))
				;
	}



}
