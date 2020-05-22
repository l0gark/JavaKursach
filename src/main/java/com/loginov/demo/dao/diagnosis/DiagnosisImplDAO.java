package com.loginov.demo.dao.diagnosis;

import com.loginov.demo.model.Diagnosis;
import com.loginov.demo.repository.DiagnosisRepository;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
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

    @Override
    public Diagnosis getDiagnosisByName(String name) {
        final Diagnosis diagnosis = diagnosisRepository.findDiagnosesByName(name);
        if (diagnosis != null) {
            return diagnosis;
        }
        final Long id = insert(new Diagnosis(-1L, name));
        return getDiagnosisById(id);
    }
}
