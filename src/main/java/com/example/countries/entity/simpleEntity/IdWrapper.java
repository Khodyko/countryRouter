package com.example.countries.entity.simpleEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Component
public class IdWrapper implements Serializable {
    private static final long serialVersionUID = 1L;
    @JoinColumn(name = "id_country", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Country countryMain;
    @JoinColumn(name = "id_board_country", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Country countryBoarded;
}
