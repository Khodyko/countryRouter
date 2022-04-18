package com.example.countries.entity.request;

import lombok.Data;

import java.util.List;

@Data
public class CountryRequest {
    private Long id;
    private String name;
    private String code;
    private Double latitude;
    private Double longitude;
    private List<String> codesOfBoardedCountries;
}
