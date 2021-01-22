package de.academy.services;

import de.academy.dao.ProfessorDAO;
import de.academy.dto.ProfessorDTO;
import de.academy.entities.Professor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/* Service for registering professors for and deregistering professors from lectures */
@Service
public class LectureRegistrationServiceImpl implements LectureRegistrationService {

    private final ProfessorDAO professorDAO;
    private final ProfessorService professorService;

    public LectureRegistrationServiceImpl(ProfessorDAO professorDAO, ProfessorService professorService) {
        this.professorDAO = professorDAO;
        this.professorService = professorService;
    }

    @Override
    @Transactional
    public ProfessorDTO registerProfessorForLecture(Long professorId, long lectureId) {

        Professor updatedProfessor = professorDAO.addLectureToProfessor(professorId, lectureId);
        return professorService.mapProfessorToProfessorDTO(updatedProfessor);
    }

    @Override
    @Transactional
    public ProfessorDTO deregisterProfessorFromLecture(Long professorId, long lectureId) {

        Professor updatedProfessor = professorDAO.removeLectureFromProfessor(professorId, lectureId);
        return professorService.mapProfessorToProfessorDTO(updatedProfessor);
    }
}
