package com.example.countries.service;

import com.example.countries.entity.simpleEntity.Rout;

/**
 * This interface realised search of rout between the countries.
 * Every method realise concrete algorithm.
 */
//I had a problem of understanding "what algorithm is"
//In my opinion we have algorithm of searching between the countries.
//On the other hand we have a distance calculating algorithm.
public interface RoutService {
    Rout getRouteByHaversine(String from, String to);
}
