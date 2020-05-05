package com.loginov.demo.controller;

import com.loginov.demo.dao.diagnosis.DiagnosisDAO;
import com.loginov.demo.model.Diagnosis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DiagnosisController {

    private final DiagnosisDAO diagnosisDAO;

    @Autowired
    public DiagnosisController(final DiagnosisDAO diagnosisDAO) {
        this.diagnosisDAO = diagnosisDAO;
    }

    @GetMapping("/diagnosis")
    public @ResponseBody
    Diagnosis getDiagnosisById(@RequestParam int id) {
        return diagnosisDAO.getDiagnosisById(id);
    }

    @GetMapping("/diagnoses")
    public @ResponseBody
    List<Diagnosis> getDiagnoses() {
        return diagnosisDAO.getAllDiagnosis();
    }

    @PostMapping("/diagnosis")
    public @ResponseBody
    ResponseEntity<HttpStatus> insert(@RequestBody Diagnosis diagnosis) {
        int id = diagnosisDAO.insert(diagnosis);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
