package org.projekat.repositorys;

import org.projekat.model.Role;

public interface RoleRepository {
    Role findByName(String name);
}
