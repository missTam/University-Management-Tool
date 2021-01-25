package de.academy.services;

import de.academy.dao.RoleDAO;
import de.academy.dao.UserDAO;
import de.academy.dto.UserDTO;
import de.academy.entities.Student;
import de.academy.entities.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

    private final RoleDAO roleDAO;
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    public UserRegistrationServiceImpl(RoleDAO roleDAO, UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.roleDAO = roleDAO;
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void registerUser(UserDTO userDTO) {

        User user = new User();

        // assign user details to the user object
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());

        user.setStudent(new Student(userDTO.getFirstname(), userDTO.getLastname(), user));

        // give user default role of "student" as only students can register, for now
        user.setRole(roleDAO.findRoleByName("ROLE_STUDENT"));

        // persist user in the database
        userDAO.save(user);
    }
}
