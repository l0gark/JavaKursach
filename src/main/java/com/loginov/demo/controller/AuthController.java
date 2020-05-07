package com.loginov.demo.controller;

import com.loginov.demo.dao.user.UserDAO;
import com.loginov.demo.model.SimpleResponse;
import com.loginov.demo.model.auth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("auth")
@RestController
public class AuthController {

    private final UserDAO userDAO;

    @Autowired
    public AuthController(@RequestBody final UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        try {
            final User user = userDAO.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("all")
    public ResponseEntity<List<User>> getWards() {
        return ResponseEntity.ok(userDAO.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<SimpleResponse> insert(@RequestBody User user) {
        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            return ResponseEntity.badRequest().body(SimpleResponse.of(HttpStatus.BAD_REQUEST));
        }

        if(!userDAO.insert(user)){
            return ResponseEntity.badRequest().body(SimpleResponse.of(HttpStatus.BAD_REQUEST));
        }

        return ResponseEntity.ok(SimpleResponse.of(HttpStatus.OK));
    }
}
