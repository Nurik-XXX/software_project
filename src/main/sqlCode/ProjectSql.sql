select * from students;

create table students(
	student_id INT PRIMARY KEY,
	student_password VARCHAR(32),
	student_name VARCHAR(32),
	student_surname VARCHAR(32),
	student_email VARCHAR(32),
	student_phone INT,
	student_course INT,
	student_gender VARCHAR(32)
);

DELETE FROM students;

DROP TABLE teachers,enrollments,courses,grades
DROP TABLE students

SELECT * FROM teachers;

DELETE FROM teachers;

CREATE TABLE teachers(
	teacher_id SERIAL  PRIMARY KEY,
	teacher_name VARCHAR(32),
	teacher_surname VARCHAR(32),
	teacher_email VARCHAR(32),
	teacher_phone INT,
	teacher_gender VARCHAR(32)
);

SELECT * FROM courses;

DELETE from courses;

CREATE TABLE courses(
	course_id VARCHAR(32) PRIMARY KEY,
	course_name VARCHAR(32),
	instructor_id INT REFERENCES teachers(teacher_id),
	course_credits INT
);

SELECT * FROM enrollments;

DELETE from enrollments;

DROP TABLE enrollments;

CREATE TABLE enrollments(
	enrollment_id SERIAL PRIMARY KEY,
	student_id INT REFERENCES students(student_id),
	course_id VARCHAR(32) REFERENCES courses(course_id)
);

SELECT * FROM courses c
JOIN enrollments e ON e.course_id = c.course_id
WHERE e.student_id = 220103044;

SELECT * from grades;

DELETE FROM grades;

CREATE TABLE grades(
    grade_id SERIAL PRIMARY KEY,
    student_id INT REFERENCES students(student_id),
    course_id VARCHAR(32) REFERENCES courses(course_id),
    grade_percentage INT
)



SELECT c.course_id,c.course_name,g.grade_percentage
FROM grades g
JOIN courses c ON g.course_id = c.course_id
WHERE student_id = 123456789;

UPDATE grades SET grade_percentage=4 WHERE student_id=123456789 AND course_id = 'MAT251'