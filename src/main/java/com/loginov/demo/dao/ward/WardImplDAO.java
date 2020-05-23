package com.loginov.demo.dao.ward;

import com.loginov.demo.model.Ward;
import com.loginov.demo.repository.PersonRepository;
import com.loginov.demo.repository.WardRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WardImplDAO implements WardDAO {

    private final WardRepository wardRepository;
    private final PersonRepository personRepository;

    public WardImplDAO(final WardRepository wardRepository, PersonRepository personRepository) {
        this.wardRepository = wardRepository;
        this.personRepository = personRepository;
    }

    @Override
    public Ward insert(final Ward ward) {
        return wardRepository.save(ward);
    }

    @Override
    public List<Ward> getAllWards() {
        return wardRepository.findAll();
    }

    @Override
    public Ward getWardById(final Long id) {
        return wardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Wrong id"));
    }

    @Override
    public void delete(Long id) {
        final int count = personRepository.findAllByWardId(id).size();
        if(count > 0){
            throw new IllegalArgumentException("For begin ypu should remove all persons");
        }
        wardRepository.deleteById(id);
    }
}
