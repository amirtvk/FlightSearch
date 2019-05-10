package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.provider.FlightProviderRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * Created by amir on 5/10/19.
 */
@Service
public class SearchFlightServiceImpl implements SearchFlightService {

    @Autowired
    FlightProviderRegistry flightRegistery;

    @Autowired
    SortComparatorGenerator sortComparatorGenerator;


    private ExecutorService executor;

    @Value("${executors.thread.count:25}")
    private int maxThreadsCount;

    @PostConstruct
    public void init() {
        executor = Executors.newFixedThreadPool(maxThreadsCount);
    }

    @Override
    public List<BusyFlightsResponse> search(BusyFlightsRequest request) {

        return flightRegistery.getProviders()
                .stream()
                .map(provider -> executor.submit(() -> provider.searchFilghts(request)))
                .flatMap(l -> getFutureResult(l).stream())
//                .sorted(Comparator.comparingDouble(BusyFlightsResponse::getFare))
                .collect(Collectors.toList());
    }

    private List<BusyFlightsResponse> getFutureResult(Future<List<BusyFlightsResponse>> future) {
        try {
            return future.get();
        } catch (InterruptedException|ExecutionException e) {
//            log.error("Error fetching response from supplier {}", e);
            return new ArrayList();
        }
    }
}
