package com.vti.templaterestfulapi.repositories;


import com.vti.templaterestfulapi.models.ERole;
import com.vti.templaterestfulapi.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);

}
