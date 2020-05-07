package com.loginov.demo.dao.user;

import com.loginov.demo.model.auth.Role;
import com.loginov.demo.model.auth.User;
import com.loginov.demo.repository.auth.UserRepository;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserImplDAO implements UserDAO {

    @NonNull
    private final UserRepository userRepository;

    @NonNull
    private final BCryptPasswordEncoder passwordEncoder;

    public UserImplDAO(@NonNull final UserRepository userRepository,
                       @NonNull final BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean insert(User user) {
        final User userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }

        user.setRoles(Collections.singleton(Role.user()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(final Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Wrong id"));
    }

    @Override
    public boolean deleteUser(Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username " + username + " is not exist");
        }

        return user;
    }
}
