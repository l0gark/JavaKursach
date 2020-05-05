package com.loginov.demo.dao.diagnosis;

import com.loginov.demo.model.Diagnosis;
import com.loginov.demo.model.Ward;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DiagnosisDAO {
    int insert(Diagnosis diagnosis);

    List<Diagnosis> getAllDiagnosis();

    Diagnosis getDiagnosisById(int id);
}
