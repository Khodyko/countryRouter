package com.example.countries.config;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

/**
 * This class used for configuration mapstruct interfaces.
 * @see com.example.countries.converter.CountryConverter
 */
@MapperConfig(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface MapstructConfiguration {
}
