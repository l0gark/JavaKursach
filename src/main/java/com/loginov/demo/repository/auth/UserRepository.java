package com.loginov.demo.repository.auth;

import com.loginov.demo.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
