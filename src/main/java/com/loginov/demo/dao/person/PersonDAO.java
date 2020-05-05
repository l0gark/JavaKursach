package com.loginov.demo.dao.person;

import com.loginov.demo.model.Person;
import com.loginov.demo.model.dto.PersonDto;

import java.util.List;

public interface PersonDAO {
    void insert(Person person);

    List<PersonDto> getAllPersons();

    PersonDto getPersonById(int id);
}
