package com.example.countries.service.impl;

import com.example.countries.entity.simpleEntity.Country;
import com.example.countries.entity.simpleEntity.CountryBoardPair;
import com.example.countries.entity.simpleEntity.IdWrapper;
import com.example.countries.entity.simpleEntity.Rout;
import com.example.countries.repository.CountryRepo;
import com.example.countries.service.DistanceCalculator;
import com.example.countries.service.RoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.font.TextHitInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoutServiceImpl implements RoutService {

    private final CountryRepo countryRepo;
    private final DistanceCalculator distanceCalculator;


    @Override
    public Rout getRouteByHaversine(String from, String to) {
        List<Country> routCountryList = new ArrayList<>();
        List<Country> usedCountries = new ArrayList<>();
        Country countryFrom = countryRepo.findCountryByCode(from);
        routCountryList.add(countryFrom);

        Country countryTo = countryRepo.findCountryByCode(to);
        List<Country> countryBoardedListFrom = this.getBoardedCountries(countryFrom);
        List<Country> countryBoardedListTo = this.getBoardedCountries(countryTo);
        if (countryBoardedListFrom.isEmpty() || countryBoardedListTo.isEmpty()) {
            return null;
        }
        usedCountries.add(countryFrom);
        Double minLength = Double.MAX_VALUE;
        Double tempLength;
        Country searchCountry=null;
        List<Country> badEndPointCountryList=new ArrayList<>();
        badEndPointCountryList.add(countryFrom);
        while (true) {
            if (countryBoardedListFrom.contains(countryTo)) {
                routCountryList.add(countryTo);
                System.out.println("the end");
                return new Rout(routCountryList.stream().map(Country::getCode).collect(Collectors.toList()));
            }
            if (usedCountries.containsAll(countryBoardedListFrom)) {
                if(badEndPointCountryList.containsAll(usedCountries)){
                return null;}
                else{
                    //step back
                    System.out.println("step back");
                    usedCountries.clear();
                    //repeated entities?
                    usedCountries.addAll(badEndPointCountryList);
                    System.out.println("bad search country "+ searchCountry.getCode());
                    badEndPointCountryList.add(searchCountry);
                    routCountryList.clear();
                    searchCountry=countryFrom;
                    routCountryList.add(countryFrom);
                }
            }
            for (int i = 0; i < countryBoardedListFrom.size(); i++) {
                System.out.println("name country "+countryBoardedListFrom.get(i).getCode());
                if (usedCountries.contains(countryBoardedListFrom.get(i))) {
                    continue;
                }
                tempLength = distanceCalculator.getDistByHaversine(countryBoardedListFrom.get(i).getLatitude(),
                        countryBoardedListFrom.get(i).getLongitude(), countryTo.getLatitude(), countryTo.getLongitude());
                System.out.println("length= " + tempLength);
                System.out.println("minLength= " + minLength);
                if (tempLength < minLength) {
                    searchCountry = countryBoardedListFrom.get(i);
                    minLength = tempLength;
                }
            }
            System.out.println("Take country "+ searchCountry.getCode());
            routCountryList.add(searchCountry);
            usedCountries.addAll(countryBoardedListFrom);
            countryBoardedListFrom= this.getBoardedCountries(searchCountry);
            minLength=Double.MAX_VALUE;
        }

    }

    private List<Country> getBoardedCountries(Country country){
        return country.getCountryBoardPairs().stream()
                .map(CountryBoardPair::getIdWrapper)
                .map(IdWrapper::getCountryBoarded).collect(Collectors.toList());
    }

}
