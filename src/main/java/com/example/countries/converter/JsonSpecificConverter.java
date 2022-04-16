package com.example.countries.converter;

import com.example.countries.entity.Country;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;

/**
 * This converter is used for reading from a specific json (task file)
 * to the Country entity and List of boards countries codes(String).
 * At the start of app this class used be Initializer
 * @see Country
 * @see com.example.countries.config.Initializer
 */
@Component
public class JsonSpecificConverter {

//fixme naming?????
    public Map<Country, List<Long>> getCountryFromFile(String jsonPath) {
        Map<Country, List<Long>> jsonInfo = null;
        try (Reader reader = new FileReader("src/main/resources/json/countries.json")) {
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(reader);
            for (Object o : jsonArray) {
                if (o instanceof JSONObject) {
                    JSONObject name = (JSONObject) ((JSONObject) o).get("name");
                    System.out.println(name.get("common"));
                    System.out.println(((JSONObject) o).get("cca3"));
                    JSONArray latlng = (JSONArray) ((JSONObject) o).get("latlng");
                    System.out.println(latlng.get(0));
                    System.out.println(latlng.get(1));
                    JSONArray borders = (JSONArray) ((JSONObject) o).get("borders");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonInfo;
    }


}
