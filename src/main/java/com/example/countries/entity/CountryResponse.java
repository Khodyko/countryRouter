package com.example.countries.entity;

import lombok.Data;

import java.util.List;

@Data
public class CountryResponse {
    private Long id;
    private String name;
    private String code;
    private Double latitude;
    private Double longitude;
    private List<String> boardCountryCodes;
}
