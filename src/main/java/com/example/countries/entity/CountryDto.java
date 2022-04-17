package com.example.countries.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryDto {
    private Long id;
    private String name;
    private String code;
    private Double latitude;
    private Double longitude;
    private List<String> codesOfBoardedCountries;

    public CountryDto(String name, String code, Double latitude, Double longitude, List<String> boardCountryCodes) {
        this.name = name;
        this.code = code;
        this.latitude = latitude;
        this.longitude = longitude;
        this.codesOfBoardedCountries = boardCountryCodes;
    }
}
