package com.example.countries.entity.simple;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This entity is container for Strings.
 * Every String is code of Country.
 * Order of these codes is rout from country to other one.
 *
 * @see Country
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rout {
    List<String> rout;
}
