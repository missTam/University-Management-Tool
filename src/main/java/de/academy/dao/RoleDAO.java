package de.academy.dao;

import de.academy.entities.Role;

public interface RoleDAO {

	Role findRoleByName(String roleName);
}
