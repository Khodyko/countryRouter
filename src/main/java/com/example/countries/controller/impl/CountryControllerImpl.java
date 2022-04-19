package com.example.countries.controller.impl;

import com.example.countries.controller.CountryController;
import com.example.countries.converter.CountryConverter;
import com.example.countries.entity.dto.CountryDto;
import com.example.countries.entity.request.CountryRequest;
import com.example.countries.entity.response.CountryResponse;
import com.example.countries.service.impl.CountryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor

public class CountryControllerImpl implements CountryController {

    private final CountryServiceImpl countryService;
    private final CountryConverter converter;

    @Override
    public List<CountryResponse> getCountryList() {
        return countryService.getCountryList().stream()
                .map(converter::DtoToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CountryResponse createCountry(CountryRequest countryRequest) {
        if(countryRequest.getId()!=null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);}
        CountryDto countryDto = converter.requestToDto(countryRequest);
        System.out.println(countryDto);
        System.out.println(countryRequest);
        CountryDto countryDtoFromDb = countryService.createCountry(countryDto);
        return converter.DtoToResponse(countryDtoFromDb);
    }

    @Override
    public CountryResponse putCountry(CountryRequest countryRequest) {
        if(countryRequest.getId()==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);}
        CountryDto countryDto = converter.requestToDto(countryRequest);
        CountryDto countryDtoFromDb = countryService.putCountry(countryDto);
        return converter.DtoToResponse(countryDtoFromDb);
    }

    @Override
    public void deleteCountry(Long id) {
        countryService.deleteCountry(id);
    }
}
