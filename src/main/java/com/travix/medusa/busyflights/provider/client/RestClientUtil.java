package com.travix.medusa.busyflights.provider.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

/**
 * Created by amir on 5/12/19.
 */
public class RestClientUtil {

    public static MultiValueMap<String, String> convertEntityToQueryParam(Object obj){
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> map = objectMapper.convertValue(obj, new TypeReference<Map<String,String>>() {});

        // Map to MultiValueMap
        LinkedMultiValueMap<String, String> linkedMultiValueMap = new LinkedMultiValueMap<>();
        map.entrySet().forEach(e -> linkedMultiValueMap.add(e.getKey(), e.getValue()));
        return linkedMultiValueMap;
    }
}
