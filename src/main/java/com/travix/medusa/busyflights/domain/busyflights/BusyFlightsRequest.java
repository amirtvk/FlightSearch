package com.travix.medusa.busyflights.domain.busyflights;

import com.travix.medusa.busyflights.domain.BaseRequest;
import com.travix.medusa.busyflights.controller.validation.IataFormat;
import com.travix.medusa.busyflights.controller.validation.TimeFormat;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Size;
/*
    This class inherit from BaseRequest class in order to add sort and projection functionality
*/

@NoArgsConstructor
@Data
@EqualsAndHashCode
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


    @Builder
    public BusyFlightsRequest(String sort,
                              String projection,
                              String origin,
                              String destination,
                              String departureDate,
                              String returnDate,
                              int numberOfPassengers) {
        super(sort, projection);
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.numberOfPassengers = numberOfPassengers;
    }

}
