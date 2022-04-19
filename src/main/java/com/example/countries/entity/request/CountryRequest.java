package com.example.countries.entity.request;

import lombok.Data;


import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class CountryRequest {

    private Long id;
    @Size(min = 2, max=250)
    @NotNull
    private String name;
    @Size(min = 3, max=3)
    @NotNull
    private String code;
    @DecimalMax(value = "90")
    @DecimalMin(value = "-90")
    @NotNull
    private Double latitude;
    @DecimalMax(value = "180.0")
    @DecimalMin(value = "-180.0")
    @NotNull
    private Double longitude;
    private List<String> codesOfBoardedCountries;
}
