CREATE TABLE student (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    age INTEGER NOT NULL,
    date_of_birth DATE NOT NULL,
    email TEXT NOT NULL UNIQUE,
    gender TEXT NOT NULL,
    name TEXT NOT NULL
);

CREATE TABLE average (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    average DOUBLE PRECISION NOT NULL,
    student_id BIGSERIAL NOT NULL UNIQUE,
    FOREIGN KEY (student_id) REFERENCES student(id)
);

CREATE SEQUENCE average_sequence
INCREMENT 1;

CREATE TABLE grade (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    grade_scale TEXT NOT NULL,
    grade_weight INTEGER NOT NULL,
    insertion_date DATE NOT NULL,
    student_id BIGSERIAL NOT NULL,
    FOREIGN KEY (student_id) REFERENCES student(id)
);

CREATE INDEX student_id_average
ON average (student_id); 

CREATE INDEX student_id_grade
ON grade (student_id); 
