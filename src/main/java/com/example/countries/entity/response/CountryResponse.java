package com.example.countries.entity.response;

import lombok.Data;

import java.util.List;

/**
 * It's Responce entity of Country Entity.
 * @see com.example.countries.entity.simpleEntity.Country
 */
@Data
public class CountryResponse {
    private Long id;
    private String name;
    private String code;
    private Double latitude;
    private Double longitude;
    private List<String> codesOfBoardedCountries;
}
