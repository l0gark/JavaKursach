package com.loginov.demo.controller;

import com.loginov.demo.dao.person.PersonDAO;
import com.loginov.demo.model.Person;
import com.loginov.demo.model.dto.PersonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    private final PersonDAO personDAO;

    @Autowired
    public PersonController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping("/person")
    public @ResponseBody
    PersonDto getPersonById(@RequestParam int id) {
        return personDAO.getPersonById(id);
    }

    @GetMapping("/persons")
    public @ResponseBody
    List<PersonDto> getPersons() {
        return personDAO.getAllPersons();
    }

    @PostMapping("/person")
    public @ResponseBody
    ResponseEntity<HttpStatus> insert(@RequestBody final Person person) {
        personDAO.insert(person);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
