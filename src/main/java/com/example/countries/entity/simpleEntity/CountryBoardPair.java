package com.example.countries.entity.simpleEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table(name = "country_board_pair")
@AllArgsConstructor
@NoArgsConstructor
public class CountryBoardPair implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    IdWrapper idWrapper;
}
