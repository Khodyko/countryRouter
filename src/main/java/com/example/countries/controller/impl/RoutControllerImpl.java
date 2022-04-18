package com.example.countries.controller.impl;

import com.example.countries.controller.RoutController;
import com.example.countries.entity.simpleEntity.Rout;
import com.example.countries.service.RoutService;
import com.example.countries.service.impl.RoutServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class RoutControllerImpl implements RoutController {
    private final RoutService routServiceImpl;

    //fixme Validate all inn data
    @Override
    public Rout getRouteFromTo(String from, String to) {
        System.out.println(1);
        if(routServiceImpl.getRouteByHaversine(from,to)==null){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);}
        else {
        return routServiceImpl.getRouteByHaversine(from,to);}
    }

    @Override
    public String getRoutExceptionAlgorithm(String algorithm, String from, String to) {
        System.out.println(2);
        return null;
    }
}
