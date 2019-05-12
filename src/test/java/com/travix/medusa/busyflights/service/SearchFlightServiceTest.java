package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.BaseTest;
import com.travix.medusa.busyflights.TestUtil;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.provider.CrazyAirProvider;
import com.travix.medusa.busyflights.provider.FlightProviderRegistry;
import com.travix.medusa.busyflights.provider.ToughJetProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.any;

/**
 * Created by amir on 5/12/19.
 */
@ContextConfiguration(classes = {
        SearchFlightServiceImpl.class,
        FlightProviderRegistry.class,
        CrazyAirProvider.class,
        ToughJetProvider.class,
        SortCommandGenerator.class,
        ProjectionCommandGenerator.class
})
@RunWith(SpringJUnit4ClassRunner.class)
public class SearchFlightServiceTest extends BaseTest {


    @Autowired
    SearchFlightServiceImpl service;

    @Autowired
    SortCommandGenerator sortCommandGenerator;

    @Autowired
    ProjectionCommandGenerator projectionCommandGenerator;

    @MockBean
    FlightProviderRegistry registry;

    @MockBean
    CrazyAirProvider crazyAirProvider;

    @MockBean
    ToughJetProvider toughJetProvider;


    @Test
    public void serviceShouldReturnAllClientsResults(){
        BusyFlightsRequest request = TestUtil.getRandomBusyFlightsRequest();
        BusyFlightsResponse response1 = TestUtil.getRandomBusyFlightsResponse(request);
        BusyFlightsResponse response2 = TestUtil.getRandomBusyFlightsResponse(request);

        Mockito.when(crazyAirProvider.searchFlights(any(BusyFlightsRequest.class)))
                .thenReturn(Arrays.asList(response1));

        Mockito.when(toughJetProvider.searchFlights(any(BusyFlightsRequest.class)))
                .thenReturn(Arrays.asList(response2));

        Mockito.when(registry.getProviders()).thenReturn(Arrays.asList(crazyAirProvider,toughJetProvider));


        List<BusyFlightsResponse> result = service.search(request);
        Assert.assertEquals(2, result.size());

        //To Check the result is sorted
        List<BusyFlightsResponse> expectedResult = response1.getFare() < response2.getFare()
                ? Arrays.asList(response1, response2)
                : Arrays.asList(response2, response1);

        Assert.assertArrayEquals(expectedResult.toArray() , result.toArray());

    }

    @Test
    public void serviceShouldReturnResultsWhenOneClientHasError(){
        BusyFlightsRequest request = TestUtil.getRandomBusyFlightsRequest();
        BusyFlightsResponse response1 = TestUtil.getRandomBusyFlightsResponse(request);

        Mockito.when(crazyAirProvider.searchFlights(any(BusyFlightsRequest.class)))
                .thenReturn(Arrays.asList(response1));

        Mockito.when(toughJetProvider.searchFlights(any(BusyFlightsRequest.class)))
                .thenThrow(new RuntimeException("Error"));

        Mockito.when(registry.getProviders()).thenReturn(Arrays.asList(crazyAirProvider,toughJetProvider));


        List<BusyFlightsResponse> result = service.search(request);
        Assert.assertEquals(1, result.size());

        Assert.assertEquals(response1, result.get(0));
    }


}
