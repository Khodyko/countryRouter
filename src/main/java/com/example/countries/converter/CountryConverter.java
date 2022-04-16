package com.example.countries.converter;

import com.example.countries.entity.Country;
import com.example.countries.entity.CountryDto;
import com.example.countries.entity.CountryRequest;
import com.example.countries.entity.CountryResponse;
import org.mapstruct.Mapper;

import java.util.List;

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
Country DtoToCountry(CountryDto countryDto);
CountryDto CountryToDto(Country country, List<String> boardCountryCodes);

}
