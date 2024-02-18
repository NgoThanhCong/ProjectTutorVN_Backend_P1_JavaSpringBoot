package com.greenwich.tutorvn.repository;

import com.greenwich.tutorvn.model.ERole;
import com.greenwich.tutorvn.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
