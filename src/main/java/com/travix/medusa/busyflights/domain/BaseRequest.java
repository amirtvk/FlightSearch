package com.travix.medusa.busyflights.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Optional;

@AllArgsConstructor
@Data
public class BaseRequest {

    private String sort;
    private String projection;

    public BaseRequest() {
    }

}
