package de.academy.dao;

import de.academy.entities.User;

public interface UserDAO {

    User findByUsername(String username);

    User save(User user);
}
