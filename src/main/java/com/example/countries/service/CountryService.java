package com.example.countries.service;

import com.example.countries.entity.CountryDto;

import java.util.LinkedList;
import java.util.List;

public interface CountryService {
    //fixme transactions!!!!
    //fixme add algorithm choice mechanism
    LinkedList<String> getRouteFromTo(String algorithm, String from, String to);
    List<CountryDto> getCountryList();
    CountryDto createCountry(CountryDto countryDto) throws Exception;
    CountryDto putCountry(CountryDto countryDto);
    void deleteCountry(Long id);

}
