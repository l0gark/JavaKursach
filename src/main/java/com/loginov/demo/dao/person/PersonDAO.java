package com.loginov.demo.dao.person;

import com.loginov.demo.model.Person;
import com.loginov.demo.model.dto.PersonCreateDto;

import java.util.List;

public interface PersonDAO {
    Person insert(PersonCreateDto personDto);

    List<Person> getAllPersons();

    Person getPersonById(Long id);

    List<Person> getPersonsByWardId(Long wardId);
}
