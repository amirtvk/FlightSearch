package com.travix.medusa.busyflights.domain.toughjet;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ToughJetResponse {

    private String carrier;
    private double basePrice;
    private double tax;
    private double discount;
    private String departureAirportName;
    private String arrivalAirportName;
    private String outboundDateTime;
    private String inboundDateTime;

}
