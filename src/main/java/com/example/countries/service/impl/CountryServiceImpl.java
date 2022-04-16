package com.example.countries.service.impl;

import com.example.countries.converter.CountryConverter;
import com.example.countries.entity.Country;
import com.example.countries.entity.CountryBoard;
import com.example.countries.entity.CountryDto;
import com.example.countries.repository.CountryBoardsRepository;
import com.example.countries.repository.CountryRepository;
import com.example.countries.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepo;
    private final CountryConverter converter;
    private final CountryBoardsRepository countryBoardsRepository;

    @Override
    public LinkedList<String> getRouteFromTo(String algorithm, String from, String to) {
        return null;
    }

    @Override
    public List<CountryDto> getCountryList(CountryDto countryDto) {

        return null;
    }

    @Override
    public CountryDto createCountry(CountryDto countryDto) {
        Country countryForSaving=countryRepo.save(converter.DtoToCountry(countryDto));
        Long countryForSavingId=countryForSaving.getId();
        //fixme make it by one call of DB
        List<Country> countryBoardedList = countryDto.getBoardCountryCodes()
                .stream().map(countryRepo::getCountryByCode)
                .collect(Collectors.toList());
        for (int i = 0; i <countryBoardedList.size() ; i++) {
            countryBoardsRepository.save(
                    new CountryBoard(countryForSavingId,countryBoardedList.get(i).getId()));

        }

        return null;
    }

    @Override
    public CountryDto putCountry(CountryDto countryDto) {
        return null;
    }

    @Override
    public void deleteCountry(Long id) {

    }

    public void createCountryList(List<CountryDto> countryDtoList) {
        Map<Country, List<Long>> countryBroadCountryIdMap=new HashMap<>();
        for (CountryDto countryDto:countryDtoList) {

        }

    }
}
