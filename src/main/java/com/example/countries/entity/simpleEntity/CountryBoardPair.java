package com.example.countries.entity.simpleEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * It's entity of relation Country and BoardCountry
 * (Country entity ManyToMany relation to itself).
 * EmbeddedId field (IdWrapper) is used to realise composite key
 * for Hibernate.
 * Specific Getters, Setters, Constructor are
 * for more comfortable call of entity.
 *
 * @see Country
 * @see IdWrapper
 */
@Entity
@Data
@Table(name = "country_board_pair")
@AllArgsConstructor
@NoArgsConstructor
public class CountryBoardPair implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    IdWrapper idWrapper;

    public CountryBoardPair(Country countryMain, Country countryBoarded) {
        this.idWrapper =new IdWrapper(countryMain,countryBoarded);
    }

    public Country getCountryMain() {
        return idWrapper.getCountryMain();
    }

    public Country getCountryBoarded() {
        return idWrapper.getCountryBoarded();
    }

    public void setCountryMain(Country countryMain) {
        this.idWrapper.setCountryMain(countryMain);
    }

    public void setCountryBoarded(Country countryBoarded) {
        this.idWrapper.setCountryMain(countryBoarded);
    }
}
