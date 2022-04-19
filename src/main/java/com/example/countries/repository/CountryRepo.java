package com.example.countries.repository;

import com.example.countries.entity.simple.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * It's Repository of Country entity.
 *
 * @see Country
 */
@Repository
public interface CountryRepo extends CrudRepository<Country,Long> {
    Country findCountryByCode(String code);


}
