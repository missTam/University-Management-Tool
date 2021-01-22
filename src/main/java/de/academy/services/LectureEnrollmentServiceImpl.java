package de.academy.services;

import de.academy.dao.LectureDAO;
import de.academy.dao.StudentDAO;
import de.academy.dto.StudentDTO;
import de.academy.entities.Lecture;
import de.academy.entities.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/* Service for enrolling students to and disenrolling students from lectures */
@Service
public class LectureEnrollmentServiceImpl implements LectureEnrollmentService {

    private final StudentDAO studentDAO;
    private final LectureDAO lectureDAO;
    private final StudentService studentService;

    public LectureEnrollmentServiceImpl(StudentDAO studentDAO, LectureDAO lectureDAO, StudentService studentService) {
        this.studentDAO = studentDAO;
        this.lectureDAO = lectureDAO;
        this.studentService = studentService;
    }

    @Override
    @Transactional
    public StudentDTO enrollStudentInLecture(long studentId, long lectureId) {

        Student updatedStudent = studentDAO.addLectureToStudent(studentId, lectureId);

        return updateStudentLecturesWithProfessorInformation(updatedStudent);
    }

    @Override
    @Transactional
    public StudentDTO disenrollStudentFromLecture(long studentId, long lectureId) {

        Student updatedStudent = studentDAO.removeLectureFromStudent(studentId, lectureId);

        return updateStudentLecturesWithProfessorInformation(updatedStudent);
    }

    private StudentDTO updateStudentLecturesWithProfessorInformation(Student student) {

        Set<Lecture> lecturesWithProfessorInformation = lectureDAO.getAllLecturesForStudent(student);
        return studentService.mapStudentToStudentDTO(student, lecturesWithProfessorInformation);
    }
}
