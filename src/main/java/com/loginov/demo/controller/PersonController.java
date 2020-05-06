package com.loginov.demo.controller;

import com.loginov.demo.dao.person.PersonDAO;
import com.loginov.demo.model.Person;
import com.loginov.demo.model.SimpleResponse;
import com.loginov.demo.model.dto.PersonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("person")
@RestController
public class PersonController {

    private final PersonDAO personDAO;

    @Autowired
    public PersonController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping("{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        try {
            final Person person = personDAO.getPersonById(id);
            return ResponseEntity.ok(person);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("all")
    public List<Person> getPersons() {
        return personDAO.getAllPersons();
    }

    @PostMapping
    public ResponseEntity<SimpleResponse> insert(@RequestBody final PersonDto person) {
        personDAO.insert(person);
        return ResponseEntity.ok(SimpleResponse.of(HttpStatus.OK));
    }
}
