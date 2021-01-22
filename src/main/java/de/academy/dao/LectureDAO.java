package de.academy.dao;


import de.academy.entities.Lecture;
import de.academy.entities.Student;

import java.util.Set;


public interface LectureDAO {

    Set<Lecture> findAll();

    Set<Lecture> getAllLecturesWithProfessor();

    Set<Lecture> getAllLecturesForStudent(Student student);
}
