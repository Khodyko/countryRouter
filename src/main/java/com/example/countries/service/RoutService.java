package com.example.countries.service;

import com.example.countries.entity.simpleEntity.Rout;

public interface RoutService {
    Rout getRouteByHaversine(String from, String to);
}
