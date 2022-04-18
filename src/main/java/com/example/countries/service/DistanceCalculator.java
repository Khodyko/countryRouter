package com.example.countries.service;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class DistanceCalculator {
    public Double getDistByHaversine(Double lat1, Double lng1, Double lat2, Double lng2) {
        Double earthRadius = 6371000d; //meters
        Double dLat = Math.toRadians(lat2-lat1);
        Double dLng = Math.toRadians(lng2-lng1);
        Double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        Double dist = (earthRadius * c);
        return dist;
    }
}
