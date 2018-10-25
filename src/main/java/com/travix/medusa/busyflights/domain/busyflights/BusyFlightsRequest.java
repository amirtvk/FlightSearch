package com.travix.medusa.busyflights.domain.busyflights;

import com.travix.medusa.busyflights.domain.BaseRequest;
import com.travix.medusa.busyflights.validation.IataValue;
import lombok.Data;

import javax.validation.constraints.NotNull;
/*
    This class inherit from BaseRequest class in order to add sort  functionality
*/

@Data
public class BusyFlightsRequest extends BaseRequest {

    @NotNull
    @IataValue
    private String origin;

    @NotNull
    @IataValue
    private String destination;


    private String departureDate;
    private String returnDate;
    private int numberOfPassengers;
}
