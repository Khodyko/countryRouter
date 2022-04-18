package com.example.countries.controller;

import com.example.countries.entity.request.CountryRequest;
import com.example.countries.entity.response.CountryResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RequestMapping("/countries")
public interface CountryController {



    /**
     * It seems, pagination is needed there. But task doesn't contain that.
     * @return
     */
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping
    List<CountryResponse> getCountryList();

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    CountryResponse createCountry(@RequestBody CountryRequest countryRequest);

    @ResponseStatus(code = HttpStatus.OK)
    @PutMapping
    CountryResponse putCountry(@RequestBody CountryRequest countryRequest);

    @ResponseStatus(code = HttpStatus.OK)
    @DeleteMapping("/{id}")
    void deleteCountry(@PathVariable Long id);
}
