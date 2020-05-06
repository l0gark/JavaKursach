package com.loginov.demo.dao.person;

import com.loginov.demo.model.Person;
import com.loginov.demo.model.dto.PersonDto;

import java.util.List;

public interface PersonDAO {
    void insert(PersonDto personDto);

    List<Person> getAllPersons();

    Person getPersonById(Long id);
}
