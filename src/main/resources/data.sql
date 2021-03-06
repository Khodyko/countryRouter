DROP TABLE IF EXISTS country;
DROP TABLE IF EXISTS country_board_pair;

CREATE TABLE country
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    name      VARCHAR(250) NOT NULL UNIQUE ,
    code      VARCHAR(3)   NOT NULL UNIQUE,
    latitude  DOUBLE       NOT NULL,
    longitude DOUBLE       NOT NULL
);

CREATE TABLE country_board_pair
(
    id_country       INT NOT NULL REFERENCES country (id),
    id_board_country INT NOT NULL REFERENCES country (id),
    CONSTRAINT fk_country FOREIGN KEY (id_country) REFERENCES country (id),
    CONSTRAINT fk_board_country FOREIGN KEY (id_board_country) REFERENCES country (id),
    CONSTRAINT pk_country PRIMARY KEY (id_country,id_board_country)
);
