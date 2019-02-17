CREATE TABLE logins
(
    id serial NOT NULL PRIMARY KEY,
    login varchar(30) NOT NULL UNIQUE,
    password varchar(30) NOT NULL

);

INSERT INTO logins(login, password) VALUES ('superuser', '123');
INSERT INTO logins(login, password) VALUES ('dummyuser', '321');

-- CREATE TABLE mentors
-- (
--     id serial NOT NULL PRIMARY KEY,
--     id_logins integer NOT NULL REFERENCES logins(id),
--     name varchar(30) NOT NULL,
--     surname varchar(60) NOT NULL,
--     email varchar(45) NOT NULL,
--     city varchar(60) NOT NULL,
--     begin_work date NOT NULL
-- );
--
--
-- CREATE TABLE achievements (
--   id SERIAL PRIMARY KEY,
--   name varchar(50) UNIQUE,
--   description varchar(100) NOT NULL,
--   experience integer NOT NULL,
--   tier integer,
--   id_creator integer REFERENCES mentors(id),
--   id_modifier integer REFERENCES mentors(id)
-- );

