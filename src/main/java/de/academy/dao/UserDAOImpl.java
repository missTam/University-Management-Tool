package de.academy.dao;

import de.academy.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {

    // Inject the session factory
    private final SessionFactory sessionFactory;

    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User findByUsername(String username) {

        // Get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // Retrieve/read from database using username
        Query<User> query = currentSession.createQuery("from User where username=:userName", User.class);
        query.setParameter("userName", username);

        User user = null;
        try {
            user = query.getSingleResult();
        } catch (Exception e) {
           // LOG
        }

        return user;
    }

    @Override
    public User save(User user) {
        sessionFactory.getCurrentSession().persist(user);
        return user;
    }
}
