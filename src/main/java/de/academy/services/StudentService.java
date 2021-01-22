package de.academy.services;

import de.academy.dto.StudentDTO;
import de.academy.entities.Lecture;
import de.academy.entities.Student;
import de.academy.entities.User;

import java.util.List;
import java.util.Set;

public interface StudentService {

    List<Student> getAllStudents();

    StudentDTO getStudentUnderGivenUserAccount(User authenticatedUser);

    StudentDTO mapStudentToStudentDTO(Student student, Set<Lecture> lectures);
}