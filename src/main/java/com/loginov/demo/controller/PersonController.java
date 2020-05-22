package com.loginov.demo.controller;

import com.loginov.demo.dao.person.PersonDAO;
import com.loginov.demo.model.Person;
import com.loginov.demo.model.SimpleResponse;
import com.loginov.demo.model.dto.PersonCreateDto;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@Api(value = "Person")
@RequestMapping("person")
@RestController
public class PersonController {

    private final PersonDAO personDAO;

    @Autowired
    public PersonController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @ApiOperation(value = "Return person by id", response = Person.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
    })
    @GetMapping("{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        try {
            final Person person = personDAO.getPersonById(id);
            return ResponseEntity.ok(person);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/ward/{wardId}")
    public ResponseEntity<List<Person>> getPersonsByWardId(@PathVariable Long wardId) {
        try {
            final List<Person> persons = personDAO.getPersonsByWardId(wardId);
            return ResponseEntity.ok(persons);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @ApiOperation(value = "View list of all persons in hospital", response = List.class)
    @ApiResponse(code = 200, message = "OK")
    @GetMapping("all")
    public List<Person> getPersons() {
        return personDAO.getAllPersons();
    }

    @ApiOperation(value = "Create new Person", response = SimpleResponse.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @PostMapping
    public ResponseEntity<Person> insert(@ApiParam(value = "Person object", required = true) @RequestBody final PersonCreateDto person) {
        try {
            return ResponseEntity.ok(personDAO.insert(person));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
