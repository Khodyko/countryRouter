package com.example.countries.repository;

import com.example.countries.entity.simple.CountryBoardPair;
import com.example.countries.entity.simple.IdWrapper;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * It's repository of CountryBoardPair entity.
 *
 * @see CountryBoardPair
 */
@Repository
public interface CountryBoardPairRepo extends CrudRepository<CountryBoardPair, IdWrapper> {

    void deleteAllByIdWrapper_CountryMain_Id(Long Id);
}
