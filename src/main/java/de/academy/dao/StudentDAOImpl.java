package de.academy.dao;

import de.academy.entities.Lecture;
import de.academy.entities.Student;
import de.academy.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    private final SessionFactory sessionFactory;

    public StudentDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Student> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Student order by lastName", Student.class)
                .getResultList();
    }

    @Override
    public Student getStudentByUser(User user) {

        // look up student under the given user account
        return sessionFactory
                .getCurrentSession()
                .createQuery("select s from Student s " +
                                "where s.user.id=:userID",
                        Student.class)
                .setParameter("userID", user.getId())
                .getSingleResult();
    }

    @Override
    public Student removeLectureFromStudent(long studentId, long lectureId) {

        Session session = sessionFactory.getCurrentSession();

        Lecture lecture = session.get(Lecture.class, lectureId);
        Student student = session.get(Student.class, studentId);

        student.removeLecture(lecture.getId());
        session.merge(student);

        return student;
    }

    @Override
    public Student addLectureToStudent(long studentId, long lectureId) {

        Session session = sessionFactory.getCurrentSession();

        Lecture newLecture = session.get(Lecture.class, lectureId);
        Student student = session.get(Student.class, studentId);

        student.addLecture(newLecture);
        session.merge(student);

        return student;
    }
}
