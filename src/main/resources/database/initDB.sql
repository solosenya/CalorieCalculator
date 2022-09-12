DROP TABLE IF EXISTS eatenDB;

CREATE TABLE IF NOT EXISTS dishesDB
(
    id    BIGSERIAL PRIMARY KEY ,
    name  VARCHAR(50) NOT NULL ,
    proteins double precision NOT NULL ,
    carbs double precision  NOT NULL ,
    lipids double precision  NOT NULL ,
    kilo_calories double precision  NOT NULL
    );

CREATE TABLE IF NOT EXISTS eatenDB
(
    id    BIGSERIAL PRIMARY KEY ,
    food  VARCHAR(50) NOT NULL ,
    mass double precision NOT NULL
    );

CREATE TABLE IF NOT EXISTS usersDB
(
    id    BIGSERIAL PRIMARY KEY ,
    username  VARCHAR(100) NOT NULL ,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(100) NOT NULL
);