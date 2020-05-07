package com.loginov.demo.dao.diagnosis;

import com.loginov.demo.model.Diagnosis;
import com.loginov.demo.repository.DiagnosisRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DiagnosisImplDAO implements DiagnosisDAO {

    private final DiagnosisRepository diagnosisRepository;

    public DiagnosisImplDAO(final DiagnosisRepository diagnosisRepository) {
        this.diagnosisRepository = diagnosisRepository;
    }

    @Override
    public Long insert(@NonNull final Diagnosis diagnosis) {
        return diagnosisRepository.save(diagnosis).getId();
    }

    @Override
    public List<Diagnosis> getAllDiagnosis() {
        return diagnosisRepository.findAll();
    }

    @Override
    public Diagnosis getDiagnosisById(Long id) {
        return diagnosisRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Wrong id"));
    }
}
