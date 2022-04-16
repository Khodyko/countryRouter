package com.example.countries.config;

import com.example.countries.entity.Country;
import com.example.countries.converter.JsonSpecificConverter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class Initializer implements InitializingBean {
    @Value("${init.data.json}")
    private String jsonPath;

    private final JsonSpecificConverter jsonSpecificMapper;

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<Country, List<Long>> jsonInfo = jsonSpecificMapper.getCountryFromFile(jsonPath);
    }
}
