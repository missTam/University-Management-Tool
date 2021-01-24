USE `uni-management-db`;

LOCK TABLES role WRITE;
INSERT INTO role (name)
VALUES ('ROLE_STUDENT'), ('ROLE_PROFESSOR');
UNLOCK TABLES;

LOCK TABLES user WRITE;
INSERT INTO user (username, password, email, role_id)
VALUES ('max', '$2a$10$YrKcRiCz2c7uM7Poma968.4U4QaPaUjyGPj11FzSaggJv9ZnT1kRi', 'max.mustermann@uni.com', 1),
		('melissa', '$2a$10$YrKcRiCz2c7uM7Poma968.4U4QaPaUjyGPj11FzSaggJv9ZnT1kRi', 'melissa.gomez@uni.com', 1),
        ('david', '$2a$10$YrKcRiCz2c7uM7Poma968.4U4QaPaUjyGPj11FzSaggJv9ZnT1kRi', 'david.summer', 1),
        ('john', '$2a$10$YrKcRiCz2c7uM7Poma968.4U4QaPaUjyGPj11FzSaggJv9ZnT1kRi', 'john.doe@uni.com', 1),
        ('marko', '$2a$10$YrKcRiCz2c7uM7Poma968.4U4QaPaUjyGPj11FzSaggJv9ZnT1kRi', 'marko.markovic@uni.com', 1),
		('kim', '$2a$10$YrKcRiCz2c7uM7Poma968.4U4QaPaUjyGPj11FzSaggJv9ZnT1kRi', 'kim.lee@uni.com', 2),
        ('jordan', '$2a$10$YrKcRiCz2c7uM7Poma968.4U4QaPaUjyGPj11FzSaggJv9ZnT1kRi', 'jordan.smith@uni.com', 2),
        ('chad', '$2a$10$YrKcRiCz2c7uM7Poma968.4U4QaPaUjyGPj11FzSaggJv9ZnT1kRi', 'chad.williams@uni.com', 2),
        ('joana', '$2a$10$YrKcRiCz2c7uM7Poma968.4U4QaPaUjyGPj11FzSaggJv9ZnT1kRi', 'joana.blee@uni.com', 2);
UNLOCK TABLES;

LOCK TABLES professor WRITE;
INSERT INTO professor (first_name, last_name, expertise, room, user_id)
VALUES ('Kim', 'Lee', 'Computer Science', 'A120', 6),
	('Jordan', 'Smith', 'Philosophy', 'B112', 7),
	('Chad', 'Williams', 'Mathematics', 'F127', 8),
    ('Joana', 'Blee', 'Psychology', 'C195', 9);
UNLOCK TABLES;

LOCK TABLES student WRITE;
INSERT INTO student (first_name, last_name, semester, user_id)
VALUES ('Max', 'Mustermann', 9, 1),
		('Melissa', 'Gomez', 3, 2),
        ('David', 'Summer', 10, 3),
        ('John', 'Doe', 5, 4),
       ('Marko', 'Markovic', 11, 5);
UNLOCK TABLES;

LOCK TABLES assistant WRITE;
INSERT INTO assistant (first_name, last_name, professor_id) VALUES ('Leana', 'Swift', 2),('Jimmy', 'Goodman', 1), ('Cheng', 'Xao', 2);
UNLOCK TABLES;

LOCK TABLES lecture WRITE;
INSERT INTO lecture (name, credits, start_date, end_date, professor_id)
VALUES ('Algorithms and Data Structures', 10, '2021-02-05 00:00:00', '2021-06-25 00:00:00', 1),
       ('Calculus', 5, '2021-02-10 00:00:00', '2021-06-27 00:00:00', 3),
       ('Linear Algebra', 6, '2021-02-10 00:00:00', '2021-06-27 00:00:00', 3),
       ('Ethics', 3, '2021-02-20 00:00:00', '2021-06-30 00:00:00', 2),
       ('Cognitive Sciences', 10, '2021-02-02 00:00:00', '2021-06-15 00:00:00', 4);
INSERT INTO lecture (name, credits, start_date, end_date)
VALUES ('Algorithms and Data Structures', 10, '2021-02-05 00:00:00', '2021-06-25 00:00:00'),
       ('Ethics', 3, '2021-02-20 00:00:00', '2021-06-30 00:00:00'),
       ('Cognitive Sciences', 10, '2021-02-02 00:00:00', '2021-06-15 00:00:00');
INSERT INTO lecture (name, credits, professor_id)
VALUES ('Metaphysics and Epistemology', 5, 2),
       ('Introduction to Statistics', 3, 4);
UNLOCK TABLES;

LOCK TABLES lecture_student WRITE;
INSERT INTO lecture_student (lecture_id, student_id)
VALUES (1, 2), (2, 2), (3, 2);
UNLOCK TABLES;