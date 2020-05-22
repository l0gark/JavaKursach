package com.loginov.demo.dao.person;

import com.loginov.demo.dao.diagnosis.DiagnosisDAO;
import com.loginov.demo.dao.ward.WardDAO;
import com.loginov.demo.model.Diagnosis;
import com.loginov.demo.model.Person;
import com.loginov.demo.model.Ward;
import com.loginov.demo.model.dto.PersonCreateDto;
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
    public void insert(final PersonCreateDto personDto) {
        final Person person = fromCreateDto(personDto);
        final List<Person> personsInWard = getPersonsByWardId(person.getWard().getId());
        if (personsInWard.size() >= person.getWard().getMaxCount()) {
            throw new IllegalArgumentException("Ward is full");
        }

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

    private Person fromCreateDto(final PersonCreateDto dto) {
        final Ward ward = wardDAO.getWardById(dto.getWardId());
        final Diagnosis diagnosis = diagnosisDAO.getDiagnosisByName(dto.getDiagnosisName());

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
