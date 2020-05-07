package com.loginov.demo.dao.auth.user;

import com.loginov.demo.model.Diagnosis;
import com.loginov.demo.model.auth.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserDAO extends UserDetailsService {
    Long insert(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    Boolean deleteUser(Long id);
}
