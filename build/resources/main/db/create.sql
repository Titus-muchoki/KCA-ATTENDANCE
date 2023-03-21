CREATE DATABASE kca;
\c kca;
CREATE TABLE students (id SERIAL PRIMARY KEY, name VARCHAR, reg VARCHAR, email VARCHAR, tel VARCHAR,  unit VARCHAR, lecture VARCHAR, datetaught VARCHAR, studentRemark VARCHAR, courseid INTEGER );
CREATE TABLE courses (id SERIAL PRIMARY KEY, name VARCHAR);
CREATE DATABASE  kca_test WITH TEMPLATE  kca;