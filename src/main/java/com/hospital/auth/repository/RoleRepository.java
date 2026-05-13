package com.hospital.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.auth.entity.Role;
import com.hospital.common.enums.RoleType;

public interface RoleRepository
        extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleType name);
}