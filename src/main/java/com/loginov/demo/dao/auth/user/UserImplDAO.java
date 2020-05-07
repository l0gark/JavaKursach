package com.loginov.demo.dao.auth.user;

import com.loginov.demo.model.auth.Role;
import com.loginov.demo.model.auth.User;
import com.loginov.demo.repository.auth.RoleRepository;
import com.loginov.demo.repository.auth.UserRepository;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserImplDAO implements UserDAO {

    @NonNull
    private final UserRepository userRepository;

    @NonNull
    private final RoleRepository roleRepository;

    public UserImplDAO(@NonNull final UserRepository userRepository, @NonNull final RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Long insert(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return -1L;
        }

        user.setRoles(Collections.singleton(Role.user()));
        //TODO fix
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user).getId();
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
    public Boolean deleteUser(Long id) {
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
