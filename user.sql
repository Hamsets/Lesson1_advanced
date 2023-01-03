CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY UNIQUE ,
    first_Name VARCHAR (50),
    last_Name VARCHAR (50),
    email VARCHAR (50) UNIQUE,
    "password" VARCHAR (50),
    role INTEGER,
    rating NUMERIC

);

