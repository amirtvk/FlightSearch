package com.travix.medusa.busyflights.domain.busyflights;

import com.travix.medusa.busyflights.domain.BaseRequest;
import com.travix.medusa.busyflights.validation.IataFormat;
import com.travix.medusa.busyflights.validation.TimeFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Size;
/*
    This class inherit from BaseRequest class in order to add sort  functionality
*/

@Data
public class BusyFlightsRequest extends BaseRequest {

    @Size(min = 3, max = 3)
    @IataFormat
    private String origin;

    @Size(min = 3, max = 3)
    @IataFormat
    private String destination;

    @TimeFormat
    private String departureDate;

    @TimeFormat
    private String returnDate;

    @Range(min = 1, max = 4)
    private int numberOfPassengers;
}
