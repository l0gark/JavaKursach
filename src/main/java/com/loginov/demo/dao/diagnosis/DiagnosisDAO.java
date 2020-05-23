package com.loginov.demo.dao.diagnosis;

import com.loginov.demo.model.Diagnosis;
import com.loginov.demo.model.Ward;
import com.loginov.demo.model.dto.DiagnosisDto;
import org.springframework.stereotype.Component;

import java.util.List;

public interface DiagnosisDAO {
    Long insert(Diagnosis diagnosis);

    List<Diagnosis> getAllDiagnosis();

    Diagnosis getDiagnosisById(Long id);

    Diagnosis getDiagnosisByName(String name);

    List<DiagnosisDto> getAllDiagnosisDto();

    void delete(Long id);
}
