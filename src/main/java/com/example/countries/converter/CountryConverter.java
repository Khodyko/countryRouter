package com.example.countries.converter;

import com.example.countries.entity.*;
import org.mapstruct.Mapper;

import java.util.Set;
import java.util.stream.Collectors;

/**
 *  This interface is used for converting Country entities
 * @see CountryDto
 * @see Country
 * @see CountryRequest
 * @see CountryResponse
 */


@Mapper(config = MapstructConfiguration.class)
public interface CountryConverter {

CountryDto requestToDto(CountryRequest countryRequest);
CountryRequest DtoToRequest(CountryDto countryDto);
CountryResponse DtoToResponse(CountryDto countryDto);
Country DtoToCountry(CountryDto countryDto, Set<CountryBoardPair> countryBoardPairs);
default CountryDto countryToDto(Country country){
    return new CountryDto(country.getId(),country.getName(),country.getCode(),
            country.getLatitude(), country.getLongitude(),
            country.getCountryBoardPairs().stream()
                    .map(a->a.getIdWrapper().getCountryBoarded().getCode())
                    .collect(Collectors.toList()));
}

}
