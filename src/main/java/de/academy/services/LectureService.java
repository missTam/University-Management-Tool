package de.academy.services;

import de.academy.entities.Lecture;

import java.util.Set;

public interface LectureService {

    Set<Lecture> getAllLectures();

    Set<Lecture> getAllUnregisteredLecturesForStudent(Set<Lecture> registeredLectures);
}
