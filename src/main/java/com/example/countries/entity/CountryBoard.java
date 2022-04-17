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
    //    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @Id
//    @JoinColumn(name = "id_country", referencedColumnName = "id")
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Country countryMain;
//    @Id
//    @JoinColumn(name = "id_board_country", referencedColumnName = "id")
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Country countryBoarded;
    @EmbeddedId
    CountryBoardId countryBoardId;
}
