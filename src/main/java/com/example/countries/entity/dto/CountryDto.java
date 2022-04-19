package com.example.countries.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * It's DTO entity of Country Entity.
 * @see com.example.countries.entity.simple.Country
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryDto {
    private Long id;
    private String name;
    private String code;
    private Double latitude;
    private Double longitude;
    /**
     * Call method of repo to convert
     * this field into Set<CountryBoardPair> countryBoardPairs
     * of Country Entity.
     */
    private List<String> codesOfBoardedCountries;

    public CountryDto(String name, String code, Double latitude, Double longitude, List<String> boardCountryCodes) {
        this.name = name;
        this.code = code;
        this.latitude = latitude;
        this.longitude = longitude;
        this.codesOfBoardedCountries = boardCountryCodes;
    }
}
