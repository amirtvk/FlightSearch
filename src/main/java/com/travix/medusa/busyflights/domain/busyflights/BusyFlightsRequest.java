package com.travix.medusa.busyflights.domain.busyflights;

import com.travix.medusa.busyflights.domain.BaseRequest;
import lombok.Data;

import javax.validation.constraints.NotNull;

/*        This class inharit from BaseRequset class
        in order to add sort  functionality */

@Data
public class BusyFlightsRequest extends BaseRequest {

    @NotNull
    private String origin;
    @NotNull
    private String destination;
    private String departureDate;
    private String returnDate;
    private int numberOfPassengers;
}
