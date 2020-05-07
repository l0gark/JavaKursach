package com.loginov.demo.repository.auth;

import com.loginov.demo.model.auth.Role;
import com.loginov.demo.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
