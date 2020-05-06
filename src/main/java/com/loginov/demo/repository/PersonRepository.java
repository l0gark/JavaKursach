package com.loginov.demo.repository;

import com.loginov.demo.model.Person;
import com.loginov.demo.model.Ward;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
