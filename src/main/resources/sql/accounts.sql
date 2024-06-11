CREATE TABLE IF NOT EXISTS accounts (
    id SERIAL PRIMARY KEY,
    login text not null,
    password text not null
);