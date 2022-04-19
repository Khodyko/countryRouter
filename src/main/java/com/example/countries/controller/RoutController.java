package com.example.countries.controller;

import com.example.countries.entity.simple.Rout;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.constraints.Size;

/**
 * This interface call the method of Service,
 * that matches "algorithm" variable
 * @see com.example.countries.service.RoutService
 */
@RequestMapping("/routing")
public interface RoutController {

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/{algorithm}/{from}/{to}")
    Rout getRouteFromTo(@PathVariable String algorithm,
                        @Size(min=3, max = 3) @PathVariable String from,
                        @Size(min=3, max = 3)@PathVariable String to);
}
