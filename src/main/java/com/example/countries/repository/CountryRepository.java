package com.example.countries.repository;

import com.example.countries.entity.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends CrudRepository<Country,Long> {
    Country getCountryByCode(String code);
}
