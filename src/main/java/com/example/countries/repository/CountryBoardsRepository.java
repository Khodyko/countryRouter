package com.example.countries.repository;

import com.example.countries.entity.CountryBoard;
import com.example.countries.entity.CountryBoardId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryBoardsRepository extends CrudRepository<CountryBoard, CountryBoardId> {
//   CountryBoard findCountryBoardByCountryBoarded_Code(String code);
   CountryBoard findCountryBoardsByCountryBoardId_CountryBoarded_Code(String code);
   void deleteAllByCountryBoardId_CountryMain_Id(Long Id);
}
