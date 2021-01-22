package de.academy.services;

import de.academy.dao.ProfessorDAO;
import de.academy.dao.RoleDAO;
import de.academy.dao.StudentDAO;
import de.academy.dao.UserDAO;
import de.academy.entities.Role;
import de.academy.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
@Service
public class LoginServiceImpl implements LoginService {

	private final UserDAO userDAO;

	private final RoleDAO roleDAO;

	private final StudentDAO studentDAO;

	private final ProfessorDAO professorDAO;

	private final PasswordEncoder passwordEncoder;

	public LoginServiceImpl(UserDAO userDAO, RoleDAO roleDAO, StudentDAO studentDAO, ProfessorDAO professorDAO, PasswordEncoder passwordEncoder) {
		this.userDAO = userDAO;
		this.roleDAO = roleDAO;
		this.studentDAO = studentDAO;
		this.professorDAO = professorDAO;
		this.passwordEncoder = passwordEncoder;
	}

	// Where do I return VIEW? Should I overload findByUsername? Why do I need the UserDetails below?
	// Security Config User Service? Is that a service?

	@Override
	@Transactional
	public User findByUsername(String username) {
		// check the database if the user already exists
		return userDAO.findByUsername(username);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userDAO.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		// Map custom User Entity retrieved from Database to Spring's UserDetails User !!!
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				mapRoleToAuthorities(user.getRole()));
	}

	private Collection<? extends GrantedAuthority> mapRoleToAuthorities(Role role) {
		return Collections.singletonList(new SimpleGrantedAuthority(role.getName()));
	}
}