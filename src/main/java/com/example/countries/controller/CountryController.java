package com.example.countries.controller;

import com.example.countries.entity.request.CountryRequest;
import com.example.countries.entity.response.CountryResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * This interface contains main methods for work with Country entity.
 * DTO entities are used.
 * @see com.example.countries.entity.simple.Country
 * @see com.example.countries.entity.dto.CountryDto
 * @see CountryResponse
 * @see CountryRequest
 * @see com.example.countries.converter.CountryConverter
 */
@RequestMapping("/countries")
public interface CountryController {

    /**
     * It seems, pagination is needed there. But task doesn't contain that.
     */
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping
    List<CountryResponse> getCountryList();

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    CountryResponse createCountry(@Valid   @RequestBody CountryRequest countryRequest);

    @ResponseStatus(code = HttpStatus.OK)
    @PutMapping
    CountryResponse putCountry(@Valid  @RequestBody CountryRequest countryRequest);

    @ResponseStatus(code = HttpStatus.OK)
    @DeleteMapping("/{id}")
    void deleteCountry(@Min(1) @PathVariable Long id);
}
