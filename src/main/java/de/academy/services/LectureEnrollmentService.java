package de.academy.services;

import de.academy.dto.StudentDTO;

public interface LectureEnrollmentService {

    StudentDTO enrollStudentInLecture(long studentId, long lectureId);

    StudentDTO disenrollStudentFromLecture(long studentId, long lectureId);
}
