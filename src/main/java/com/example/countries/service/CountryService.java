package com.example.countries.service;

import com.example.countries.entity.dto.CountryDto;

import java.util.List;

/**
 * Service of Country Entity.
 *
 * @see CountryDto
 * @see com.example.countries.entity.simple.Country
 */
public interface CountryService {

    List<CountryDto> getCountryList();

    CountryDto createCountry(CountryDto countryDto) throws Exception;

    CountryDto putCountry(CountryDto countryDto);

    void deleteCountry(Long id);

}
