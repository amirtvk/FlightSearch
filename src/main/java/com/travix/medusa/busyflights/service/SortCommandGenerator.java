package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.BaseRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class SortCommandGenerator {

    public static final String SORT_ORDER_SEPARATOR = ",";

    public enum SortOrder{
        Ascending,
        Descending
    }

    @Value("${search.defaultSortField:fare}")
    private String defaultSortField;

    @Value("${search.defaultSortOrder:asc}")
    private String defaultSortOrder;


    public  Comparator<BusyFlightsResponse> generateSortCommand(BaseRequest request){
        String sortField = extractSortFieldName(request);
        Comparator<BusyFlightsResponse> comparator;
        switch (sortField){
            case "fare":
                comparator = Comparator.comparingDouble(BusyFlightsResponse::getFare);
                break;
           default:
               comparator = Comparator.comparingDouble(BusyFlightsResponse::getFare);
        }
        if(extractSortOrder(request).equals(SortOrder.Descending))
            return comparator.reversed();
        else
            return comparator;
    }


    public String extractSortFieldName(BaseRequest request){
        if(request.getSort() == null || request.getSort().isEmpty())
            return defaultSortField;
        return request.getSort().split(SORT_ORDER_SEPARATOR)[0];
    }



    public SortOrder extractSortOrder(BaseRequest request){
        if(request.getSort() == null || request.getSort().isEmpty() || request.getSort().split(SORT_ORDER_SEPARATOR).length == 1)
            return defaultSortOrder.equalsIgnoreCase("asc") ? SortOrder.Ascending : SortOrder.Descending;

        String[] sortParam = request.getSort().split(SORT_ORDER_SEPARATOR);
        return sortParam[1].equalsIgnoreCase("ASC") ? SortOrder.Ascending : SortOrder.Descending;
    }

}
