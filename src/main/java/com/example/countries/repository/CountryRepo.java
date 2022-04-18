package com.example.countries.repository;

import com.example.countries.entity.simpleEntity.Country;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepo extends CrudRepository<Country,Long> {
String QUERY="WITH countrys AS" +
        "(" +
        "       SELECT  id, code, latitude, longitude, name" +
        "       FROM country" +
        "       WHERE id=5" +
        "       UNION ALL" +
        "       SELECT c.id, c.code, c.latitude, c.longitude, c.name" +
        "       FROM country as c" +
        "      JOIN country_board_pair as cbpr" +
        "      ON c.id = 1" +
        "      " +
        ")" +
        "SELECT DISTINCT * FROM countrys";
    //fixme make native sql to get complicated entities
    Country findCountryByCode(String code);

    @Query(value = QUERY, nativeQuery = true)
    List<Country> getAllByCountryBoardPairsContaining();
}
