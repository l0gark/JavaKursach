package com.loginov.demo.dao.person;

import com.loginov.demo.dao.diagnosis.DiagnosisDAO;
import com.loginov.demo.dao.ward.WardDAO;
import com.loginov.demo.model.Diagnosis;
import com.loginov.demo.model.Person;
import com.loginov.demo.model.Ward;
import com.loginov.demo.model.dto.PersonDto;
import com.loginov.demo.repository.PersonRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonJdbcDAO implements PersonDAO {

    private final PersonRepository personRepository;
    private final WardDAO wardDAO;
    private final DiagnosisDAO diagnosisDAO;

    public PersonJdbcDAO(final PersonRepository personRepository, WardDAO wardDAO, DiagnosisDAO diagnosisDAO) {
        this.personRepository = personRepository;
        this.wardDAO = wardDAO;
        this.diagnosisDAO = diagnosisDAO;
    }

    @Override
    public void insert(final PersonDto personDto) {
        final Person person = fromDto(personDto);
        personRepository.save(person);
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person getPersonById(Long id) {
        return personRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Wrong id"));
    }

    private Person fromDto(final PersonDto dto) {
        final Ward ward = wardDAO.getWardById(dto.getWardId());
        final Diagnosis diagnosis = diagnosisDAO.getDiagnosisById(dto.getDiagnosisId());

        return Person.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .fatherName(dto.getFatherName())
                .ward(ward)
                .diagnosis(diagnosis)
                .id(1L)
                .build();
    }
}
