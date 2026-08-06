CREATE TYPE IF NOT EXISTS GENDER AS ENUM ('male', 'female');
CREATE TYPE IF NOT EXISTS HAIR AS ENUM ('black', 'blonde', 'red', 'colored');

create table users
(
    id         UUID PRIMARY KEY DEFAULT uuidv7(),
    login      varchar(20) NOT NULL UNIQUE,
    name       varchar(50) NOT NULL,
    age        integer     NOT NULL,
    gender     GENDER,
    hair_color HAIR
);

create table users_aud
(
    id         UUID                     NOT NULL,
    login      varchar(20)              NOT NULL,
    name       varchar(50)              NOT NULL,
    age        integer                  NOT NULL,
    gender     GENDER,
    hair_color HAIR,
    rev        timestamp with time zone NOT NULL default now(),
    revtype    integer
);
