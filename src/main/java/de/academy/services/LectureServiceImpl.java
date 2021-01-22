package de.academy.services;

import de.academy.dao.LectureDAO;
import de.academy.entities.Lecture;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LectureServiceImpl implements LectureService {

    private final LectureDAO lectureDAO;

    public LectureServiceImpl(LectureDAO lectureDAO) {
        this.lectureDAO = lectureDAO;
    }

    @Transactional
    @Override
    public Set<Lecture> getAllLectures() {
        return lectureDAO.findAll();
    }

    @Override
    @Transactional
    public Set<Lecture> getAllUnregisteredLecturesForStudent(Set<Lecture> registeredLectures) {

        Set<Lecture> lecturesWithProfessor = lectureDAO.getAllLecturesWithProfessor();

        return lecturesWithProfessor
                .stream()
                .filter(lecture -> !registeredLectures.contains(lecture))
                .collect(Collectors.toSet());
    }
}
