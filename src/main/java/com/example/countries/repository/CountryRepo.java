package com.example.countries.repository;

import com.example.countries.entity.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepo extends CrudRepository<Country,Long> {

    //fixme make native sql to get complicated entities
    Country findCountryByCode(String code);
}
