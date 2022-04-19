package com.example.countries.controller.impl;

import com.example.countries.controller.RoutController;
import com.example.countries.entity.simpleEntity.Rout;
import com.example.countries.service.RoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Locale;

@RestController
@RequiredArgsConstructor
public class RoutControllerImpl implements RoutController {
    private final RoutService routServiceImpl;

    @Override
    public Rout getRouteFromTo(String algorithm, String from, String to) {
        Rout rout = null;
        switch (algorithm.toUpperCase()) {
            case "HAV": {
                rout = routServiceImpl.getRouteByHaversine(
                        from.toUpperCase(), to.toUpperCase());
                break;
            }
            default: break;
        }
        if (rout == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else {
            return rout;
        }
    }
}
