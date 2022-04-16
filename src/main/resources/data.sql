DROP TABLE IF EXISTS country;
DROP TABLE IF EXISTS countryBoards;

CREATE TABLE country
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    name      VARCHAR(250) NOT NULL,
    code      VARCHAR(3)   NOT NULL,
    latitude  DOUBLE       NOT NULL,
    longitude DOUBLE       NOT NULL
);

CREATE TABLE country_board
(
    id               INT AUTO_INCREMENT PRIMARY KEY,
    id_country       INT NOT NULL,
    id_board_country INT NOT NULL,
    CONSTRAINT fk_country FOREIGN KEY (id_country) REFERENCES country (id),
    CONSTRAINT fk_board_country FOREIGN KEY (id_board_country) REFERENCES country (id)

);