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
        Country countryForSaving = countryRepo.save(converter.DtoToCountry(countryDto));
        Long countryForSavingId = countryForSaving.getId();
        //fixme make it by one call of DB
        List<Long> idCountryBoardedList = countryDto.getBoardCountryCodes().stream()
                .map(countryRepo::getCountryByCode)
                .map(a -> a.getId())
                .collect(Collectors.toList());
        for (int i = 0; i < idCountryBoardedList.size(); i++) {
            countryBoardsRepository.save(
                    new CountryBoard(countryForSavingId, idCountryBoardedList.get(i)));
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
        Country country = null;
        List<Long> ids = null;
        Map<Long, List<String>> countryBroadCountryIdMap = new HashMap<>();
        for (CountryDto countryDto : countryDtoList) {
            country = countryRepo.save(converter.DtoToCountry(countryDto));
            countryBroadCountryIdMap.put(country.getId(), countryDto.getBoardCountryCodes());
        }
        for (Map.Entry<Long, List<String>> entry : countryBroadCountryIdMap.entrySet()) {

                //fixme repeat (separate method)

                List<Long> idCountryBoardedList = entry.getValue().stream()
                        .map(countryRepo::getCountryByCode)
                        .map(a -> a.getId())
                        .collect(Collectors.toList());
                for (int i = 0; i < idCountryBoardedList.size(); i++) {
                    System.out.println("loop "+i);
                    Long asddadasda=idCountryBoardedList.get(i);
                    countryBoardsRepository.save(
                            new CountryBoard(entry.getKey(), asddadasda));
            }
        }
    }


}
