package com.travix.medusa.busyflights.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by amir on 5/10/19.
 */
@Service
public class FlightProviderRegistry {

    List<FlightProviderInterface> providers;

    @Autowired
    public void setProviders(List<FlightProviderInterface> providers) {
        this.providers = providers;
    }

    public List<FlightProviderInterface> getProviders() {
        return providers;
    }
}
