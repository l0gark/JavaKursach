package com.loginov.demo.controller;

import com.loginov.demo.dao.diagnosis.DiagnosisDAO;
import com.loginov.demo.model.Diagnosis;
import com.loginov.demo.model.SimpleResponse;
import com.loginov.demo.model.Ward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("diagnosis")
@RestController
public class DiagnosisController {

    private final DiagnosisDAO diagnosisDAO;

    @Autowired
    public DiagnosisController(final DiagnosisDAO diagnosisDAO) {
        this.diagnosisDAO = diagnosisDAO;
    }

    @GetMapping("{id}")
    public ResponseEntity<Diagnosis> getDiagnosisById(@PathVariable Long id) {
        try {
            final Diagnosis diagnosis = diagnosisDAO.getDiagnosisById(id);
            return ResponseEntity.ok(diagnosis);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("all")
    public ResponseEntity<List<Diagnosis>> getDiagnoses() {
        return ResponseEntity.ok(diagnosisDAO.getAllDiagnosis());
    }

    @PostMapping
    public ResponseEntity<SimpleResponse> insert(@RequestBody Diagnosis diagnosis) {
        Long id = diagnosisDAO.insert(diagnosis);
        return ResponseEntity.ok(SimpleResponse.of(HttpStatus.OK));
    }
}
