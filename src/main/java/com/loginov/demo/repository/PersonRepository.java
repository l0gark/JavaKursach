package com.loginov.demo.repository;

import com.loginov.demo.model.Person;
import com.loginov.demo.model.Ward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findAllByWardId(Long wardId);
}
