package com.example.countries.service.impl;

import com.example.countries.entity.simple.Country;
import com.example.countries.entity.simple.CountryBoardPair;
import com.example.countries.entity.simple.Rout;
import com.example.countries.repository.CountryRepo;
import com.example.countries.service.impl.routServiceHelper.DistanceCalculator;
import com.example.countries.service.RoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * It's service for calculating of the best rout from country to other one.
 * Different methods are used for different algorithms of calculating.
 */
@Service
@RequiredArgsConstructor
public class RoutServiceImpl implements RoutService {

    private final CountryRepo countryRepo;
    private final DistanceCalculator distanceCalculator;
    //It's huge method. Need refactor.
    @Override
    public Rout getRouteByHaversine(String from, String to) {
        List<Country> routCountryList = new ArrayList<>();
        List<Country> usedForSearchCountries = new ArrayList<>();
        List<Country> badEndPointCountryList = new ArrayList<>();
        Country countryFrom = countryRepo.findCountryByCode(from);
        Country countryTo = countryRepo.findCountryByCode(to);
        List<Country> countryBoardedListFrom = this.getBoardedCountries(countryFrom);
        if (countryBoardedListFrom.isEmpty() || this.getBoardedCountries(countryTo).isEmpty()) {
            return null;
        }
        usedForSearchCountries.add(countryFrom);
        //I had an idea to make entity of these values, to change
        // its condition in search. Maybe it can be more pretty. But I decided not risk.
        Double minLength = Double.MAX_VALUE;
        Double tempLength;
        Country searchCountry = null;
        routCountryList.add(countryFrom);
        badEndPointCountryList.add(countryFrom);
        while (true) {
            if (countryBoardedListFrom.contains(countryTo)) {
                routCountryList.add(countryTo);
                return new Rout(routCountryList.stream()
                        .map(Country::getCode).collect(Collectors.toList()));
            }
            //?All countries around this country are used
            if (usedForSearchCountries.containsAll(countryBoardedListFrom)) {
                //?all endpoints of app are blocked
                if (badEndPointCountryList.containsAll(usedForSearchCountries)) {
                    return null;
                } else {
                    //start from the scratch
                    usedForSearchCountries.clear();
                    usedForSearchCountries.addAll(badEndPointCountryList);
                    //end point is blocked for using
                    badEndPointCountryList.add(searchCountry);
                    routCountryList.clear();
                    searchCountry = countryFrom;
                    routCountryList.add(countryFrom);
                }
            }
            for (int i = 0; i < countryBoardedListFrom.size(); i++) {
                //skip if country was used in search
                if (usedForSearchCountries.contains(countryBoardedListFrom.get(i))) {
                    continue;
                }
                tempLength = distanceCalculator.getDistByHaversine(countryBoardedListFrom.get(i).getLatitude(),
                        countryBoardedListFrom.get(i).getLongitude(), countryTo.getLatitude(), countryTo.getLongitude());
                if (tempLength < minLength) {
                    searchCountry = countryBoardedListFrom.get(i);
                    minLength = tempLength;
                }
            }
            //take the country more close to countryTo
            routCountryList.add(searchCountry);
            usedForSearchCountries.addAll(countryBoardedListFrom);
            countryBoardedListFrom = this.getBoardedCountries(searchCountry);
            minLength = Double.MAX_VALUE;
        }
    }

    private List<Country> getBoardedCountries(Country country) {
        return country.getCountryBoardPairs().stream()
                .map(CountryBoardPair::getCountryBoarded)
                .collect(Collectors.toList());
    }

}
