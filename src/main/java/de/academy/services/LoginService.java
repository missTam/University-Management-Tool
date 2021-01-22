package de.academy.services;

import de.academy.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

// Custom User service needs to extend Spring's UserDetailsService
public interface LoginService extends UserDetailsService {
    User findByUsername(String username);
}
