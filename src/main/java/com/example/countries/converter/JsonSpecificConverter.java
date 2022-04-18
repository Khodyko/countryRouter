package com.example.countries.converter;

import com.example.countries.entity.simpleEntity.Country;
import com.example.countries.entity.dto.CountryDto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * This converter is used for reading from a specific json (task file)
 * to the Country entity and List of boards countries codes(String).
 * At the start of app this class used be Initializer
 *
 * @see Country
 * @see com.example.countries.config.Initializer
 */
@Component
public class JsonSpecificConverter {

    public List<CountryDto> getCountryFromFile(String jsonPath) {
        List<CountryDto> countryDtoList = new ArrayList<>();
        CountryDto countryDto = null;
        List<String> list = null;
        try (Reader reader = new FileReader(jsonPath)) {
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(reader);
            for (Object o : jsonArray) {
                if (o instanceof JSONObject) {
                    JSONObject name = (JSONObject) ((JSONObject) o).get("name");
                    String common = (String) name.get("common");
                    String cca3 = (String) ((JSONObject) o).get("cca3");
                    JSONArray latlng = (JSONArray) ((JSONObject) o).get("latlng");
                    Double latitude = Double.valueOf(String.valueOf(latlng.get(0)));
                    Double longitude = Double.valueOf(String.valueOf(latlng.get(1)));
                    JSONArray borders = (JSONArray) ((JSONObject) o).get("borders");
                    list = new ArrayList<>();
                    for (int i = 0; i < borders.size(); i++) {
                        list.add(borders.get(i).toString());
                    }
                    countryDto = new CountryDto(common, cca3, latitude, longitude, list);
                    countryDtoList.add(countryDto);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return countryDtoList;
    }


}
