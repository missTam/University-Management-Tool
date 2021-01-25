package de.academy.dao;

import de.academy.entities.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDAOImpl implements RoleDAO {

	private final SessionFactory sessionFactory;

	public RoleDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Role findRoleByName(String roleName) {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<Role> theQuery = currentSession.createQuery("from Role where name=:roleName", Role.class);
		theQuery.setParameter("roleName", roleName);
		
		Role role = null;
		try {
			role = theQuery.getSingleResult();
		} catch (Exception e) {
			// log
		}
		
		return role;
	}
}
