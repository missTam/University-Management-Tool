package de.academy.dao;

import de.academy.entities.Lecture;
import de.academy.entities.Professor;
import de.academy.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProfessorDAOImpl implements ProfessorDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public ProfessorDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Professor> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Professor " +
                        "order by lastName",
                        Professor.class)
                .getResultList();
    }

    @Override
    public Professor getProfessorByUser(User user) {

        return sessionFactory
                .getCurrentSession()
                .createQuery("select p from Professor p " +
                                "join fetch p.lectures " +
                                "where p.user.id=:userID",
                        Professor.class)
                .setParameter("userID", user.getId())
                .getSingleResult();
    }

    @Override
    public Professor addLectureToProfessor(Long professorId, long lectureId) {

        Session session = sessionFactory.getCurrentSession();

        Professor professor = session.get(Professor.class, professorId);
        Lecture newLecture = session.get(Lecture.class, lectureId);

        professor.addLecture(newLecture);
        session.merge(professor);

        return professor;
    }

    @Override
    public Professor removeLectureFromProfessor(Long professorId, long lectureId) {

        Session session = sessionFactory.getCurrentSession();

        Professor professor = session.get(Professor.class, professorId);
        Lecture lecture = session.get(Lecture.class, lectureId);

        professor.removeLecture(lecture);
        session.merge(professor);

        return professor;
    }
}
