package de.academy.services;

import de.academy.dto.ProfessorDTO;

public interface LectureRegistrationService {

    ProfessorDTO registerProfessorForLecture(Long professorId, long lectureId);

    ProfessorDTO deregisterProfessorFromLecture(Long professorId, long lectureId);
}
