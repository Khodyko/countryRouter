package com.example.countries.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "country_board")
@AllArgsConstructor
@NoArgsConstructor
public class CountryBoard implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "id_country")
//    @ManyToOne(targetEntity = Country.class)
    private Long idCountry;

    @JoinColumn(name="id_board_country")
//    @ManyToOne(targetEntity = Country.class)
    private Long idBoardingCountry;

    public CountryBoard(Long idCountry, Long idBoardingCountry) {
        this.idCountry = idCountry;
        this.idBoardingCountry = idBoardingCountry;
    }
}
