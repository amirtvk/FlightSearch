package com.travix.medusa.busyflights.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BaseRequest {

    private String sort;

    public BaseRequest() {
    }

}
