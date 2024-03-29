package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.provider.FlightProviderRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    private static final Logger logger = LoggerFactory.getLogger(SearchFlightServiceImpl.class);

    @Autowired
    FlightProviderRegistry flightRegistery;

    @Autowired
    SortCommandGenerator sortCommandGenerator;

    @Autowired
    ProjectionCommandGenerator projectionCommandGenerator;

    private ExecutorService executor;

    @Value("${executors.thread.count:25}")
    private int maxThreadsCount;

    @PostConstruct
    public void init() {
        executor = Executors.newFixedThreadPool(maxThreadsCount);
    }

    @Override
    public List<BusyFlightsResponse> search(BusyFlightsRequest request) {

        logger.debug("search for flights {}" , request );
        return flightRegistery.getProviders()
                .stream()
                .map(provider -> executor.submit(() -> provider.searchFlights(request)))
                .flatMap(l -> getFutureResult(l).stream())
                .sorted(sortCommandGenerator.generateSortCommand(request))
                .filter( bfr -> Optional.ofNullable(request.getProjection()).isPresent() )
                .map(projectionCommandGenerator.generateProjectionCommand(request))
                .collect(Collectors.toList());
    }

    private List<BusyFlightsResponse> getFutureResult(Future<List<BusyFlightsResponse>> future) {
        try {
            return future.get();
        } catch (InterruptedException e) {
            logger.error("Error getting response from provider {}", e);
            return new ArrayList();
        }  catch (ExecutionException e) {
            logger.error("Error getting response from provider {}", e);
            return new ArrayList();
        }
    }
}
