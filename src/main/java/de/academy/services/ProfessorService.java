package de.academy.services;

import de.academy.entities.Professor;
import de.academy.entities.User;

import java.util.List;

public interface ProfessorService {

    List<Professor> getAllProfessors();

    de.academy.dto.ProfessorDTO getProfessorUnderGivenUserAccount(User authenticatedUser);

    de.academy.dto.ProfessorDTO mapProfessorToProfessorDTO(Professor professor);
}
