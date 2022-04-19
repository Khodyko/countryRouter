package com.example.countries.converter;

import com.example.countries.entity.dto.CountryDto;
import com.example.countries.entity.simpleEntity.Country;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * This converter is used for reading from a specific json (task file)
 * to the Country entity and List of boards countries codes(String).
 * At the start of app this class used by Initializer
 *
 * @see Country
 * @see com.example.countries.config.Initializer
 */
@Component
public class JsonSpecificConverter {

    public List<CountryDto> getCountryFromFile(String jsonUrlPath) {
        List<CountryDto> countryDtoList = new ArrayList<>();
        CountryDto countryDto;
        List<String> codeOfBorderedCountries;
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new URL(jsonUrlPath).openStream());
             Reader reader = new InputStreamReader(bufferedInputStream)) {
            JSONParser parser = new JSONParser();

            JSONArray jsonArray = (JSONArray) parser.parse(reader);
            for (Object o : jsonArray) {
                if (o instanceof JSONObject) {
                    //validate?
                    JSONObject name = (JSONObject) ((JSONObject) o).get("name");
                    String common = (String) name.get("common");
                    String cca3 = (String) ((JSONObject) o).get("cca3");
                    JSONArray latlng = (JSONArray) ((JSONObject) o).get("latlng");
                    Double latitude = Double.valueOf(String.valueOf(latlng.get(0)));
                    Double longitude = Double.valueOf(String.valueOf(latlng.get(1)));
                    JSONArray borders = (JSONArray) ((JSONObject) o).get("borders");
                    codeOfBorderedCountries = new ArrayList<>();
                    for (Object border : borders) {
                        codeOfBorderedCountries.add(border.toString());
                    }
                    countryDto = new CountryDto(common, cca3, latitude, longitude, codeOfBorderedCountries);
                    countryDtoList.add(countryDto);
                }
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return countryDtoList;
    }
}
