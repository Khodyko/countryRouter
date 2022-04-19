package com.example.countries.controller;

import com.example.countries.entity.request.CountryRequest;
import com.example.countries.entity.response.CountryResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
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
    CountryResponse createCountry(@Valid   @RequestBody CountryRequest countryRequest);

    @ResponseStatus(code = HttpStatus.OK)
    @PutMapping
    CountryResponse putCountry(@Valid  @RequestBody CountryRequest countryRequest);

    @ResponseStatus(code = HttpStatus.OK)
    @DeleteMapping("/{id}")
    void deleteCountry(@Min(1) @PathVariable Long id);
}
