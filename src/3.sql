UPDATE users SET first_name = 'Vasia', last_name = 'Vremenny', email = 'vas@gmail.com', password = 'fgee', role = 1, rating = 0.0 WHERE id = 3;
SELECT 'id', 'email','password','first_name','last_name' FROM information_schema.table_constraints WHERE table_schema = 'public' AND table_name = 'users';
SELECT 'id', 'email','password','first_name','last_name' FROM information_schema.column_options WHERE table_schema = 'public' AND table_name = 'users';
SELECT 'column_name' AS "Column", 'data_type' AS "Type", 'collation_name' AS "Collation", 'is_nullable' AS "Nullable"   FROM information_schema.columns WHERE table_schema = 'public' AND table_name = 'users';
SELECT *   FROM information_schema.columns WHERE table_schema = 'public' AND table_name = 'users';


--Initialazing for bookstore
TRUNCATE users;

DROP TABLE IF EXISTS users;	
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY UNIQUE ,
    first_name VARCHAR (50),
    last_name VARCHAR (50),
    email VARCHAR (50) UNIQUE,
    "password" VARCHAR (50),
    role INTEGER,
    rating NUMERIC);
SELECT * FROM users;	





--Initialising for LMS
DROP TABLE IF EXISTS requests;
DROP TABLE IF EXISTS courses;
DROP TABLE IF EXISTS users;


CREATE TABLE IF NOT EXISTS users
(
    id                  BIGSERIAL PRIMARY KEY  NOT NULL,
    email               CHARACTER VARYING(100) NOT NULL,
    password            CHARACTER VARYING(50)  NOT NULL,
    first_name          CHARACTER VARYING(100) NOT NULL,
    last_name           CHARACTER VARYING(100) NOT NULL,
    patronymic_name     CHARACTER VARYING(100),
    contact_preferences CHARACTER VARYING(50)  NOT NULL,
    social_media        CHARACTER VARYING(50),
    role                CHARACTER VARYING(50)  NOT NULL,
    is_active           BOOLEAN                NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS courses(
 id                 BIGSERIAL PRIMARY KEY NOT NULL,
 title				CHARACTER VARYING (80) NOT NULL
 );

CREATE TABLE IF NOT EXISTS requests(
 id                 BIGSERIAL PRIMARY KEY NOT NULL,
 courses_id			BIGINT REFERENCES courses(id),
 users_id			BIGINT REFERENCES users(id),
 status				CHARACTER VARYING (60),
 is_deleted			BOOLEAN NOT NULL DEFAULT FALSE
 );





INSERT INTO users(email, password, first_name, last_name, patronymic_name, contact_preferences, role, social_media)
VALUES ('ivan99@gmail.com', 'jfhd86', 'Ivan', 'Ivanov', 'Ivanovich', 'TELEGRAM', 'TRAINER', '@Ivan'),
       ('petr48@gmail.com', 'fdfbbfd67sdb', 'Petr', 'Petrov', 'Petrovich', 'CELLPHONE', 'MANAGER', '+375291234567'),
       ('sidor@gmail.com', 'klkjjk', 'Sidor', 'Sidorov', 'Sidorovich', 'INSTAGRAM', 'MANAGER', '#Sidor'),
       ('jek94@gmail.com', '12qwaszx', 'Yauheni', 'Hlaholeu', 'Yayhenivich', 'TELEGRAM', 'STUDENT', '+375291234567'),
       ('sol44@yandex.by', 'qazxsw21', 'Uladzislau', 'Solovev', 'Alexandrovich', 'TELEGRAM', 'STUDENT', '#Solovey'),
       ('galina_sid@gmail.com', 'sid93LL', 'Haliana', 'Sidoric', 'Sergeevna', 'INSTAGRAM', 'STUDENT', '@Sid'),
       ('dlana@mail.ru', 'vfAz1234', 'Lana', 'Dimidova', 'Antonovna', 'TELEGRAM', 'STUDENT', '+375291234567'),
       ('AKsin@Gmail.com', '12345678OOp', 'Andrey', 'Aksenov', 'Olegovich', 'TELEGRAM', 'STUDENT', '#Android'),
       ('vagan@mail.ru', '333eeeddfd', 'Nazar', 'Vahtongov', 'Stefanovich', 'INSTAGRAM', 'STUDENT', '@Nazar'),
       ('tMin@tut.by', 'trewrg', 'Tatyana', 'Minikova', 'Mironovna', 'VIBER', 'STUDENT', '@Hleb');

INSERT INTO courses (title)
VALUES ('course_test_1'),
	('course_test_2'),
	('course_test_3'),
	('course_test_4'),
	('course_test_5'),
	('course_test_6');

INSERT INTO requests (courses_id, users_id, status)
VALUES ((SELECT id FROM courses WHERE title = 'course_test_1'), (SELECT id FROM users WHERE email='jek94@gmail.com'), 'IN_PROCESSING'),
((SELECT id FROM courses WHERE title = 'course_test_2'), (SELECT id FROM users WHERE email='sol44@yandex.by'), 'IN_PROCESSING'),
((SELECT id FROM courses WHERE title = 'course_test_3'), (SELECT id FROM users WHERE email='galina_sid@gmail.com'), 'IN_PROCESSING'),
((SELECT id FROM courses WHERE title = 'course_test_4'), (SELECT id FROM users WHERE email='dlana@mail.ru'), 'IN_PROCESSING'),
((SELECT id FROM courses WHERE title = 'course_test_5'), (SELECT id FROM users WHERE email='AKsin@Gmail.com'), 'IN_PROCESSING'),
((SELECT id FROM courses WHERE title = 'course_test_6'), (SELECT id FROM users WHERE email='vagan@mail.ru'), 'IN_PROCESSING'),
((SELECT id FROM courses WHERE title = 'course_test_6'), (SELECT id FROM users WHERE email='tMin@tut.by'), 'IN_PROCESSING');

SELECT * FROM users;
SELECT * FROM courses;
SELECT * FROM requests;







