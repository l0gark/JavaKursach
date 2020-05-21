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
public class PersonImplDAO implements PersonDAO {

    private final PersonRepository personRepository;
    private final WardDAO wardDAO;
    private final DiagnosisDAO diagnosisDAO;

    public PersonImplDAO(final PersonRepository personRepository, WardDAO wardDAO, DiagnosisDAO diagnosisDAO) {
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

    @Override
    public List<Person> getPersonsByWardId(Long wardId) {
        return personRepository.findAllByWardId(wardId);
    }

    private Person fromDto(final PersonDto dto) {
        final Ward ward = wardDAO.getWardById(dto.getWardId());
        final Diagnosis diagnosis = diagnosisDAO.getDiagnosisById(dto.getDiagnosisId());

        return new Person(
                -1L,
                dto.getFirstName(),
                dto.getLastName(),
                dto.getFatherName(),
                ward,
                diagnosis
        );
    }
}
