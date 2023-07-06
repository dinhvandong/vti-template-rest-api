package com.vti.templaterestfulapi.repositories;

import com.vti.templaterestfulapi.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // List<User> findByUserName(String userName);

    Optional<User> findByUserName(String userName);
//    Optional<User> findByEmail(String email);

    Page<User> findAllByParentID (long parentID, Pageable pageable);
    List<User> findAllByParentID (long parentID);

    Boolean existsByUserName(String userName);

    List<User> findAllByIsActive(boolean active);

//    Boolean existsByEmail(String email);
}