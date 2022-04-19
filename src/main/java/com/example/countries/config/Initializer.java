package com.example.countries.config;

import com.example.countries.converter.JsonSpecificConverter;
import com.example.countries.entity.dto.CountryDto;
import com.example.countries.service.impl.CountryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This class used for start code initialization of
 * app.
 */
@Component
@RequiredArgsConstructor
public class Initializer implements InitializingBean {

    @Value("${init.data.json.path}")
    private String jsonPath;
    private final JsonSpecificConverter jsonSpecificMapper;
    private final CountryServiceImpl countryService;

    @Override
    public void afterPropertiesSet() throws Exception {
        List<CountryDto> countryDtoList = jsonSpecificMapper.getCountryFromFile(jsonPath);
        countryService.createCountryList(countryDtoList);
    }
}
