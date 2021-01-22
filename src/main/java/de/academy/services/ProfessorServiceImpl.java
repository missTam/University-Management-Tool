package de.academy.services;

import de.academy.dao.ProfessorDAO;
import de.academy.entities.Professor;
import de.academy.entities.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    private final ProfessorDAO professorDAO;

    public ProfessorServiceImpl(ProfessorDAO professorDAO) {
        this.professorDAO = professorDAO;
    }

    @Transactional
    @Override
    public List<Professor> getAllProfessors() {
        return professorDAO.findAll();
    }

    @Override
    @Transactional
    public de.academy.dto.ProfessorDTO getProfessorUnderGivenUserAccount(User authenticatedUser) {

        Professor professor = professorDAO.getProfessorByUser(authenticatedUser);

        return mapProfessorToProfessorDTO(professor);
    }

    @Override
    public de.academy.dto.ProfessorDTO mapProfessorToProfessorDTO(Professor professor) {

        de.academy.dto.ProfessorDTO professorDTO = new de.academy.dto.ProfessorDTO();

        professorDTO.setId(professor.getId());
        professorDTO.setFirstname(professor.getFirstName());
        professorDTO.setLastname(professor.getLastName());
        professorDTO.setExpertise(professor.getExpertise());
        professorDTO.setRoom(professor.getRoom());
        professorDTO.setLectures(professor.getLectures());

        return professorDTO;
    }
}
