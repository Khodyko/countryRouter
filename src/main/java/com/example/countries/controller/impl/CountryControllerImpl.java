package com.example.countries.controller.impl;

import com.example.countries.controller.CountryController;
import com.example.countries.converter.CountryConverter;
import com.example.countries.converter.JsonSpecificConverter;
import com.example.countries.entity.CountryDto;
import com.example.countries.entity.CountryRequest;
import com.example.countries.entity.CountryResponse;
import com.example.countries.service.impl.CountryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CountryControllerImpl implements CountryController {

    private  final CountryServiceImpl countryService;

    //fixme Validate all inn data
    @Override
    public LinkedList<String> getRouteFromTo(String algorithm, String from, String to) {

        return null;
    }

    @Override
    public List<CountryResponse> getCountryList() {
        return null;
    }

    @Override
    public CountryResponse createCountry(CountryRequest countryRequest) {
        return null;
    }

    @Override
    public CountryResponse putCountry(CountryRequest countryRequest) {
        return null;
    }

    @Override
    public void deleteCountry(Long id) {

    }
}
