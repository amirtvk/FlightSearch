package com.travix.medusa.busyflights.projection;

import com.travix.medusa.busyflights.BaseTest;
import com.travix.medusa.busyflights.TestUtil;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.provider.CrazyAirProvider;
import com.travix.medusa.busyflights.provider.FlightProviderRegistry;
import com.travix.medusa.busyflights.provider.ToughJetProvider;
import com.travix.medusa.busyflights.service.ProjectionCommandGenerator;
import com.travix.medusa.busyflights.service.SearchFlightServiceImpl;
import com.travix.medusa.busyflights.service.SortCommandGenerator;
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
@ContextConfiguration(classes = {ProjectionCommandGenerator.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class SearchFlightServiceTest extends BaseTest {

    @Autowired
    ProjectionCommandGenerator generator;

    @Test
    public void shouldReturnSameObjectWhenNoProjectionHasBeenSet(){
        BusyFlightsRequest request = TestUtil.getRandomBusyFlightsRequest();
        BusyFlightsResponse response = TestUtil.getRandomBusyFlightsResponse(request);
        BusyFlightsResponse responseAfterProjection =
                generator.generateProjectionCommand(request).apply(response);
        Assert.assertEquals(response, responseAfterProjection);
    }

    @Test
    public void shouldReturnFareRoundTwoDecimal(){
        BusyFlightsRequest request = TestUtil.getRandomBusyFlightsRequest();
        BusyFlightsResponse response = TestUtil.getRandomBusyFlightsResponse(request);
        request.setProjection("fareRoundTwoDecimal");
        response.setFare(1500.12345);
        BusyFlightsResponse responseAfterProjection =
                generator.generateProjectionCommand(request).apply(response);
        Assert.assertEquals(1500.12, responseAfterProjection.getFare(), 0);

        response.setFare(1500.99);
        responseAfterProjection =
                generator.generateProjectionCommand(request).apply(response);
        Assert.assertEquals(1500.99, responseAfterProjection.getFare(), 0);

        response.setFare(1500);
        responseAfterProjection =
                generator.generateProjectionCommand(request).apply(response);
        Assert.assertEquals(1500, responseAfterProjection.getFare(), 0);
    }



}
