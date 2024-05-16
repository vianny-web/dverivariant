CREATE DATABASE door_db;

CREATE TABLE accounts (
    id SERIAL PRIMARY KEY,
    login text not null,
    password text not null
);