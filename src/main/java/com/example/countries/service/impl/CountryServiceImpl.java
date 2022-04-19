package com.example.countries.service.impl;

import com.example.countries.converter.CountryConverter;
import com.example.countries.entity.dto.CountryDto;
import com.example.countries.entity.simpleEntity.Country;
import com.example.countries.entity.simpleEntity.CountryBoardPair;
import com.example.countries.repository.CountryBoardPairRepo;
import com.example.countries.repository.CountryRepo;
import com.example.countries.service.CountryService;
import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class CountryServiceImpl implements CountryService {

    private final CountryRepo countryRepo;
    private final CountryConverter converter;
    private final CountryBoardPairRepo countryBoardsRepo;

    @Override
    public List<CountryDto> getCountryList() {
        List<Country> countryList = (List<Country>) countryRepo.findAll();
        return countryList.stream()
                .map(country -> converter.countryToDto(country)).collect(Collectors.toList());
    }

    @Override
    public CountryDto createCountry(CountryDto countryDto) {
        Country country = countryRepo.save(converter.DtoToCountry(countryDto, new HashSet<>()));
        List<Country> countryBoardedList = countryDto.getCodesOfBoardedCountries().stream()
                .map(countryRepo::findCountryByCode)
                .collect(Collectors.toList());
        Set<CountryBoardPair> countryBoardPairList = countryBoardedList.stream()
                .map(
                        countryBoarded -> new CountryBoardPair(country, countryBoarded)
                )
                .collect(Collectors.toSet());
        country.setCountryBoardPairs(countryBoardPairList);
        return converter.countryToDto(country);
    }

    @Override
    public CountryDto putCountry(CountryDto countryDto) {
        Country country = countryRepo.save(converter.DtoToCountry(countryDto, new HashSet<>()));
        List<Country> countryBoardedList = countryDto.getCodesOfBoardedCountries().stream()
                .map(countryRepo::findCountryByCode)
                .collect(Collectors.toList());
        Set<CountryBoardPair> countryBoardPairList = countryBoardedList.stream()
                .map(
                        countryBoarded -> new CountryBoardPair(country, countryBoarded)
                )
                .collect(Collectors.toSet());
        country.setCountryBoardPairs(countryBoardPairList);
        return converter.countryToDto(country);
    }

    @Override
    public void deleteCountry(Long id) {
        countryBoardsRepo.deleteAllByIdWrapper_CountryMain_Id(id);
        countryRepo.deleteById(id);
    }

    public void createCountryList(List<CountryDto> countryDtoList) {
        Country country;
        List<CountryBoardPair> countryBoardPairsForSaving = new ArrayList<>();
        Map<Country, List<String>> countryAndCodesBoardCountry = new HashMap<>();

        for (CountryDto countryDto : countryDtoList) {
            country = countryRepo.save(converter.DtoToCountry(countryDto, new HashSet<>()));
            countryAndCodesBoardCountry.put(country, countryDto.getCodesOfBoardedCountries());
        }

        for (Map.Entry<Country, List<String>> entry : countryAndCodesBoardCountry.entrySet()) {
            List<CountryBoardPair> countryBoardedList = entry.getValue().stream()
                    .map(countryRepo::findCountryByCode)
                    .map(
                            savedCountry -> new CountryBoardPair(entry.getKey(), savedCountry)
                    )
                    .collect(Collectors.toList());
            countryBoardPairsForSaving.addAll(countryBoardedList);
        }
        countryBoardsRepo.saveAll(countryBoardPairsForSaving);
    }
}
