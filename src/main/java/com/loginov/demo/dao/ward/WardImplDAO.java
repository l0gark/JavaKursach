package com.loginov.demo.dao.ward;

import com.loginov.demo.model.Ward;
import com.loginov.demo.repository.WardRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WardImplDAO implements WardDAO {

    private final WardRepository wardRepository;

    public WardImplDAO(final WardRepository wardRepository) {
        this.wardRepository = wardRepository;
    }

    @Override
    public Long insert(final Ward ward) {
        return wardRepository.save(ward).getId();
    }

    @Override
    public List<Ward> getAllWards() {
        return wardRepository.findAll();
    }

    @Override
    public Ward getWardById(final Long id) {
        return wardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Wrong id"));
    }
}
