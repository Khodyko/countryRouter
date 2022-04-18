package com.example.countries.controller;

import com.example.countries.entity.simpleEntity.Rout;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
@RequestMapping("/countries/routing")
public interface RoutController {

    //fixme algorithm choice
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/hav/{from}/{to}")
    Rout getRouteFromTo(@PathVariable String from, @PathVariable String to);

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/{algorithm}/{from}/{to}")
    String getRoutExceptionAlgorithm(@PathVariable String algorithm,
                                     @PathVariable String from, @PathVariable String to);


}