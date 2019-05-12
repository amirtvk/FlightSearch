package com.travix.medusa.busyflights.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Optional;

@AllArgsConstructor
@Data
@EqualsAndHashCode
public class BaseRequest {

    private String sort;
    private String projection;

    public BaseRequest() {
    }

}
