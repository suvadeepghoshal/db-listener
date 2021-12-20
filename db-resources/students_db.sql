-- Database: students_db

-- DROP DATABASE students_db;

CREATE DATABASE students_db
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.1252'
    LC_CTYPE = 'English_United States.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

COMMENT ON DATABASE students_db
    IS 'This is a database for storing student details used in learning spring boot';
