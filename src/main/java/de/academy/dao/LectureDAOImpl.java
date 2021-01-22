package de.academy.dao;

import de.academy.entities.Lecture;
import de.academy.entities.Student;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class LectureDAOImpl implements LectureDAO {

    private final SessionFactory sessionFactory;

    public LectureDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // join fetch used due to LazyLoadingException
    // left join fetch used because we want all lectures regardless of whether or not professor is null
    @Override
    public Set<Lecture> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("select l from Lecture l " +
                                "left join fetch l.professor",
                        Lecture.class)
                .getResultStream()
                .collect(Collectors.toSet());
    }

    // ONLY WORKS WITH EAGER INITIALIZATION; LAZY DOES NOT WORK EVEN THOUGH I DONT CALL GET PROF. (JSP TRIES TO GET PROF)

    @Override
    public Set<Lecture> getAllLecturesWithProfessor() {

        // avoid LazyInitializationException
        // join fetch - only return lectures whose professor_id != null
        return sessionFactory.getCurrentSession()
                .createQuery("select l from Lecture l " +
                                "join fetch l.professor",
                        Lecture.class)
                .getResultStream()
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Lecture> getAllLecturesForStudent(Student student) {

        return sessionFactory.getCurrentSession()
                .createQuery("select l from Lecture l " +
                                "join fetch l.students s " +
                                "join fetch l.professor " +
                                "where s.id = :studentID",
                        Lecture.class)
                .setParameter("studentID", student.getId())
                .getResultStream()
                .collect(Collectors.toSet());
    }
}
