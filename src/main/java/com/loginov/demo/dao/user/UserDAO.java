package com.loginov.demo.dao.user;

import com.loginov.demo.model.auth.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserDAO extends UserDetailsService {
    boolean insert(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    boolean deleteUser(Long id);
}
