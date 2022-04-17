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

import java.util.*;
import java.util.function.Supplier;
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
    public List<CountryDto> getCountryList() {
        List<Country> countryList = (List<Country>) countryRepo.findAll();
        return countryList.stream()
                .map(country -> converter.countryToDto(country)).collect(Collectors.toList());
    }



    @Override
    public CountryDto createCountry(CountryDto countryDto) throws Exception {
        Set<CountryBoard> countryBoardSet=new HashSet<>();
        Country countryForSaving = countryRepo.save(converter.DtoToCountry(countryDto,countryBoardSet));
        List<Country> countryBoardedList=countryDto.getCodesOfBoardedCountries()
                .stream().map(code->countryRepo.findCountryByCode(code))
                .collect(Collectors.toList());
        for (int i = 0; i < countryBoardedList.size(); i++) {
            countryBoardsRepository.save(
                    new CountryBoard(countryForSaving, countryBoardedList.get(i)));
        }
        //fixme answer without part
        return countryDto;
    }

    @Override
    public CountryDto putCountry(CountryDto countryDto) {
        Set<CountryBoard> countryBoardSet=new HashSet<>();
        Country countryForSaving = countryRepo.save(converter.DtoToCountry(countryDto,countryBoardSet));
        List<Country> countryBoardedList=countryDto.getCodesOfBoardedCountries()
                .stream().map(code->countryRepo.findCountryByCode(code))
                .collect(Collectors.toList());
        for (int i = 0; i < countryBoardedList.size(); i++) {
            countryBoardsRepository.save(
                    new CountryBoard(countryForSaving, countryBoardedList.get(i)));
        }
        //fixme make it by one call of DB
        //fixme answer without part
        return countryDto;
    }

    @Override
    public void deleteCountry(Long id) {
        countryRepo.deleteById(id);
    }

    public void createCountryList(List<CountryDto> countryDtoList) {
        Country country;
        Map<Country, List<String>> countryBroadCountryIdMap = new HashMap<>();
        for (CountryDto countryDto : countryDtoList) {
            country = countryRepo.save(converter.DtoToCountry(countryDto,
                            countryDto.getCodesOfBoardedCountries().stream()
                                    //fixme make sql
                                    .map(countryBoardsRepository::findCountryBoardByCountryBoarded_Code)
                                    .collect(Collectors.toSet()))
            );
            countryBroadCountryIdMap.put(country, countryDto.getCodesOfBoardedCountries());
        }
        for (Map.Entry<Country, List<String>> entry : countryBroadCountryIdMap.entrySet()) {
            //fixme repeat (separate method)
            List<Country> countryBoardedList = entry.getValue().stream()
                    .map(countryRepo::findCountryByCode)
                    .collect(Collectors.toList());
            for (int i = 0; i < countryBoardedList.size(); i++) {
                System.out.println("index=" + i);
                countryBoardsRepository.save(
                        new CountryBoard(entry.getKey(), countryBoardedList.get(i)));
            }
        }
    }
}
