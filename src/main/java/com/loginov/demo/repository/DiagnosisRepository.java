package com.loginov.demo.repository;

import com.loginov.demo.model.Diagnosis;
import com.loginov.demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {
}
