package com.travix.medusa.busyflights.domain.toughjet;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ToughJetRequest {

    private String from;
    private String to;
    private String outboundDate;
    private String inboundDate;
    private int numberOfAdults;

}
