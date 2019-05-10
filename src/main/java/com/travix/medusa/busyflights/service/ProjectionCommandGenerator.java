package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.BaseRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

/**
 * Created by amir on 5/11/19.
 */

@Service
public class ProjectionCommandGenerator {

    public static final String NO_PROJECTION = "noProjection";

    public Function<BusyFlightsResponse,? extends BusyFlightsResponse>
            generateProjectionCommand(BaseRequest request) {

        request.setProjection(
                Optional.ofNullable(request.getProjection()).isPresent()
                        ? request.getProjection() : NO_PROJECTION  );

            switch (request.getProjection()){

                case NO_PROJECTION:
                    return  bfr ->  bfr.toBuilder().build() ;

                case "fareRoundTwoDecimal":
                    return  bfr ->  bfr.toBuilder().fare(Math.round(bfr.getFare() * 100.0) / 100.0).build() ;

            }
        return  bfr ->  bfr.toBuilder().build() ;
    }
}
