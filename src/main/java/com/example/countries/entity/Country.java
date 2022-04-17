package com.example.countries.entity;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "country")
public class Country implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private String code;
    private Double latitude;
    private Double longitude;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idWrapper.countryMain",
    cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<CountryBoardPair> countryBoardPairs;


    public Country(String name, String code, Double latitude, Double longitude, Set<CountryBoardPair> countryBoardPairs) {
        this.name = name;
        this.code = code;
        this.latitude = latitude;
        this.longitude = longitude;
        this.countryBoardPairs = countryBoardPairs;
    }
}
