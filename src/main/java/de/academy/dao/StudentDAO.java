package de.academy.dao;

import de.academy.entities.Student;
import de.academy.entities.User;

import java.util.List;

public interface StudentDAO {

    List<Student> findAll();

    Student getStudentByUser(User user);

    Student removeLectureFromStudent(long studentId, long lectureId);

    Student addLectureToStudent(long studentId, long lectureId);
}
