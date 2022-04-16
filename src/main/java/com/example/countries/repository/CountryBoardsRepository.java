package com.example.countries.repository;

import com.example.countries.entity.CountryBoard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryBoardsRepository extends CrudRepository<CountryBoard, Long> {
}
