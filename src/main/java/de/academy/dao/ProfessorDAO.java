package de.academy.dao;

import de.academy.entities.Professor;
import de.academy.entities.User;

import java.util.List;

public interface ProfessorDAO {

    List<Professor> findAll();

    Professor getProfessorByUser(User user);

    Professor addLectureToProfessor(Long professorId, long lectureId);

    Professor removeLectureFromProfessor(Long professorId, long lectureId);
}
