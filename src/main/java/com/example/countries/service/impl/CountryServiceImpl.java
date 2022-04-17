package com.example.countries.service.impl;

import com.example.countries.converter.CountryConverter;
import com.example.countries.entity.Country;
import com.example.countries.entity.CountryBoardPair;
import com.example.countries.entity.IdWrapper;
import com.example.countries.entity.CountryDto;
import com.example.countries.repository.CountryBoardPairRepo;
import com.example.countries.repository.CountryRepo;
import com.example.countries.service.CountryService;
import lombok.AllArgsConstructor;
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
        Country country = countryRepo.save(converter.DtoToCountry(countryDto, new HashSet<CountryBoardPair>()));
        List<Country> countryBoardedList = countryDto.getCodesOfBoardedCountries()
                .stream().map(code -> countryRepo.findCountryByCode(code))
                .collect(Collectors.toList());
        List<CountryBoardPair> countryBoardPairList=countryBoardedList.stream()
                .map(countryBoarded -> new CountryBoardPair(new IdWrapper(country, countryBoarded)))
                .collect(Collectors.toList());
        countryBoardsRepo.saveAll(countryBoardPairList);
        //fixme answer without part
        return countryDto;
    }

    @Override
    public CountryDto putCountry(CountryDto countryDto) {
        Country country = countryRepo.save(converter.DtoToCountry(countryDto, new HashSet<CountryBoardPair>()));
        List<Country> countryBoardedList = countryDto.getCodesOfBoardedCountries()
                .stream().map(code -> countryRepo.findCountryByCode(code))
                .collect(Collectors.toList());
        List<CountryBoardPair> countryBoardPairList=countryBoardedList.stream()
                .map(countryBoarded -> new CountryBoardPair(new IdWrapper(country, countryBoarded)))
                .collect(Collectors.toList());
        countryBoardsRepo.saveAll(countryBoardPairList);
        //fixme make it by one call of DB
        //fixme answer without part
        return countryDto;
    }

    @Override
    public void deleteCountry(Long id) {
        countryBoardsRepo.deleteAllByIdWrapper_CountryMain_Id(id);
        countryRepo.deleteById(id);
    }

    public void createCountryList(List<CountryDto> countryDtoList) {
        Country country;
        Map<Country, List<String>> countryBroadCountryIdMap = new HashMap<>();
        for (CountryDto countryDto : countryDtoList) {
            country = countryRepo.save(converter.DtoToCountry(countryDto,
                    countryDto.getCodesOfBoardedCountries().stream()
                            .map(countryBoardsRepo::findCountryBoardPairByIdWrapper_CountryBoarded_Code)
                            .collect(Collectors.toSet()))
            );
            countryBroadCountryIdMap.put(country, countryDto.getCodesOfBoardedCountries());
        }
        for (Map.Entry<Country, List<String>> entry : countryBroadCountryIdMap.entrySet()) {
            //fixme repeat (separate method)-> go to separate service method
            List<Country> countryBoardedList = entry.getValue().stream()
                    .map(countryRepo::findCountryByCode)
                    .collect(Collectors.toList());
            for (int i = 0; i < countryBoardedList.size(); i++) {
                System.out.println("index=" + i);
                countryBoardsRepo.save(
                        new CountryBoardPair(
                                new IdWrapper(entry.getKey(), countryBoardedList.get(i))
                        ));
            }
        }
    }
}
