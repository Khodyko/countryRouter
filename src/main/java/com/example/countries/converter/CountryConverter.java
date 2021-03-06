package com.example.countries.converter;

import com.example.countries.config.MapstructConfiguration;
import com.example.countries.entity.dto.CountryDto;
import com.example.countries.entity.request.CountryRequest;
import com.example.countries.entity.response.CountryResponse;
import com.example.countries.entity.simple.Country;
import com.example.countries.entity.simple.CountryBoardPair;
import org.mapstruct.Mapper;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * This interface is used for converting Country entities.
 * @see CountryDto
 * @see Country
 * @see CountryRequest
 * @see CountryResponse
 */
@Mapper(config = MapstructConfiguration.class)
public interface CountryConverter {

    CountryDto requestToDto(CountryRequest countryRequest);

    CountryResponse dtoToResponse(CountryDto countryDto);

    Country dtoToCountry(CountryDto countryDto, Set<CountryBoardPair> countryBoardPairs);

    default CountryDto countryToDto(Country country) {
        return new CountryDto(country.getId(), country.getName(), country.getCode(),
                country.getLatitude(), country.getLongitude(),
                country.getCountryBoardPairs().stream()
                        .map(a -> a.getCountryBoarded().getCode())
                        .collect(Collectors.toList()));
    }

}
