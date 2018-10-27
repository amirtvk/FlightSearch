package com.travix.medusa.busyflights;

import com.travix.medusa.busyflights.controller.ControllerSortUtil;
import com.travix.medusa.busyflights.controller.SearchFlightsController;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Assert;

import java.util.Comparator;

@ContextConfiguration(classes = {ControllerSortUtil.class})

@RunWith(SpringJUnit4ClassRunner.class)

public class GenerateSortComparatorTest {

    @Autowired
    ControllerSortUtil controllerSortUtil;

    @Test
    public void shouldGenerateCorrectComparator(){

        BusyFlightsRequest request = TestUtil.getRandomBusyFlightsRequest();
        request.setSort("fare,asc");
        Comparator<BusyFlightsResponse> ascendingSortComparator = controllerSortUtil.generateSortComparator(request);
        BusyFlightsResponse response1 = TestUtil.getRandomBusyFlightsResponse(request);
        Double fare1 = 25.9999;
        response1.setFare(fare1);
        BusyFlightsResponse response2 = TestUtil.getRandomBusyFlightsResponse(request);
        Double fare2 = 50.9999;
        response2.setFare(fare2);
        Assert.assertEquals(-1,ascendingSortComparator.compare(response1, response2));
        Assert.assertEquals(1,ascendingSortComparator.compare(response2, response1));
        response2.setFare(fare1);
        Assert.assertEquals(0,ascendingSortComparator.compare(response1, response2));

        request.setSort("fare,desc");
        Comparator<BusyFlightsResponse> descendingSortComparator = controllerSortUtil.generateSortComparator(request);
        response1.setFare(fare1);
        response2.setFare(fare2);
        Assert.assertEquals(1,descendingSortComparator.compare(response1, response2));
        Assert.assertEquals(-1,descendingSortComparator.compare(response2, response1));
        response2.setFare(fare1);
        Assert.assertEquals(0,descendingSortComparator.compare(response1, response2));
    }

    @Test
    public void shouldSupportDefaultSortField(){

        BusyFlightsRequest request = TestUtil.getRandomBusyFlightsRequest();

        request.setSort(null);
        Comparator<BusyFlightsResponse> ascendingSortComparator = controllerSortUtil.generateSortComparator(request);
        BusyFlightsResponse response1 = TestUtil.getRandomBusyFlightsResponse(request);
        Double fare1 = 25.9999;
        response1.setFare(fare1);
        BusyFlightsResponse response2 = TestUtil.getRandomBusyFlightsResponse(request);
        Double fare2 = 50.9999;
        response2.setFare(fare2);
        Assert.assertEquals(-1,ascendingSortComparator.compare(response1, response2));
        Assert.assertEquals(1,ascendingSortComparator.compare(response2, response1));
        response2.setFare(fare1);
        Assert.assertEquals(0,ascendingSortComparator.compare(response1, response2));

        request.setSort("");
        ascendingSortComparator = controllerSortUtil.generateSortComparator(request);
        response1.setFare(fare1);
        response2.setFare(fare2);
        Assert.assertEquals(-1,ascendingSortComparator.compare(response1, response2));
        Assert.assertEquals(1,ascendingSortComparator.compare(response2, response1));
        response2.setFare(fare1);
        Assert.assertEquals(0,ascendingSortComparator.compare(response1, response2));
    }


    @Test
    public void shouldSupportDefaultSortOrder(){

        BusyFlightsRequest request = TestUtil.getRandomBusyFlightsRequest();

        request.setSort("fare");
        Comparator<BusyFlightsResponse> ascendingSortComparator = controllerSortUtil.generateSortComparator(request);
        BusyFlightsResponse response1 = TestUtil.getRandomBusyFlightsResponse(request);
        Double fare1 = 25.9999;
        response1.setFare(fare1);
        BusyFlightsResponse response2 = TestUtil.getRandomBusyFlightsResponse(request);
        Double fare2 = 50.9999;
        response2.setFare(fare2);
        Assert.assertEquals(-1,ascendingSortComparator.compare(response1, response2));
        Assert.assertEquals(1,ascendingSortComparator.compare(response2, response1));
        response2.setFare(fare1);
        Assert.assertEquals(0,ascendingSortComparator.compare(response1, response2));

    }



}
