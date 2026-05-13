package com.hospital.auth.config;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.hospital.auth.entity.Role;
import com.hospital.auth.entity.User;
import com.hospital.auth.repository.RoleRepository;
import com.hospital.auth.repository.UserRepository;
import com.hospital.common.enums.RoleType;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInitializer
        implements CommandLineRunner {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        Role adminRole =
                roleRepository.findByName(
                        RoleType.ROLE_ADMIN
                ).orElseGet(() -> {

                    Role role = new Role();

                    role.setName(RoleType.ROLE_ADMIN);

                    return roleRepository.save(role);
                });

        if (!userRepository.existsByUsername("admin")) {

            User user = new User();

            user.setUsername("admin");

            user.setEmail("admin@gmail.com");

            user.setMobile("9999999999");

            user.setPassword(
                    passwordEncoder.encode("admin123")
            );

            user.setRoles(Set.of(adminRole));

            userRepository.save(user);
        }
    }
}