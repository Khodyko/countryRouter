package com.example.countries.repository;

import com.example.countries.entity.CountryBoardPair;
import com.example.countries.entity.IdWrapper;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryBoardPairRepo extends CrudRepository<CountryBoardPair, IdWrapper> {
    CountryBoardPair findCountryBoardPairByIdWrapper_CountryBoarded_Code(String code);

    void deleteAllByIdWrapper_CountryMain_Id(Long Id);
}
