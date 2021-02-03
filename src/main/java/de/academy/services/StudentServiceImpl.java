package de.academy.services;

import de.academy.dao.LectureDAO;
import de.academy.dao.StudentDAO;
import de.academy.dto.StudentDTO;
import de.academy.entities.Lecture;
import de.academy.entities.Student;
import de.academy.entities.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentDAO studentDAO;
    private final LectureDAO lectureDAO;

    public StudentServiceImpl(StudentDAO studentDAO, LectureDAO lectureDAO) {
        this.studentDAO = studentDAO;
        this.lectureDAO = lectureDAO;
    }

    @Override
    @Transactional
    public List<Student> getAllStudents() {
        return studentDAO.findAll();
    }

    @Override
    @Transactional
    public StudentDTO getStudentUnderGivenUserAccount(User authenticatedUser) {

        Student student = studentDAO.getStudentByUser(authenticatedUser);
        Set<Lecture> lectures = lectureDAO.getAllLecturesForStudent(student);

        return mapStudentToStudentDTO(student, lectures);
    }

    @Override
    public StudentDTO mapStudentToStudentDTO(Student student, Set<Lecture> lecturesWithProfessorInformation) {

        StudentDTO studentDTO = new StudentDTO();

        studentDTO.setId(student.getId());
        studentDTO.setFirstname(student.getFirstName());
        studentDTO.setLastname(student.getLastName());
        studentDTO.setSemester(student.getSemester());
        studentDTO.setLectures(lecturesWithProfessorInformation);

        return studentDTO;
    }
}
