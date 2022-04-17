package com.example.countries.controller.impl;

import com.example.countries.controller.CountryController;
import com.example.countries.converter.CountryConverter;
import com.example.countries.entity.CountryDto;
import com.example.countries.entity.CountryRequest;
import com.example.countries.entity.CountryResponse;
import com.example.countries.service.impl.CountryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CountryControllerImpl implements CountryController {

    private  final CountryServiceImpl countryService;
    private final CountryConverter converter;

    //fixme Validate all inn data
    @Override
    public LinkedList<String> getRouteFromTo(String algorithm, String from, String to) {

        return null;
    }

    @Override
    public List<CountryResponse> getCountryList() {
      return   countryService.getCountryList().stream()
                .map(converter::DtoToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CountryResponse createCountry(CountryRequest countryRequest) throws Exception {
        CountryDto countryDto= converter.requestToDto(countryRequest);
        System.out.println(countryDto);
        System.out.println(countryRequest);
        CountryDto countryDtoFromDb=countryService.createCountry(countryDto);
        return converter.DtoToResponse(countryDtoFromDb);
    }

    @Override
    public CountryResponse putCountry(CountryRequest countryRequest) {
        CountryDto countryDto= converter.requestToDto(countryRequest);
        CountryDto countryDtoFromDb=countryService.putCountry(countryDto);
        return converter.DtoToResponse(countryDtoFromDb);
    }

    @Override
    public void deleteCountry(Long id) {
        countryService.deleteCountry(id);
    }
}
