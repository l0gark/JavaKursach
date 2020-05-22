package com.loginov.demo.controller;

import com.loginov.demo.dao.diagnosis.DiagnosisDAO;
import com.loginov.demo.model.Diagnosis;
import com.loginov.demo.model.SimpleResponse;
import com.loginov.demo.model.dto.DiagnosisDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "Return diagnosis by id", response = Diagnosis.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
    })
    @GetMapping("{id}")
    public ResponseEntity<Diagnosis> getDiagnosisById(@PathVariable Long id) {
        try {
            final Diagnosis diagnosis = diagnosisDAO.getDiagnosisById(id);
            return ResponseEntity.ok(diagnosis);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @ApiOperation(value = "View list of all diagnoses", response = List.class)
    @ApiResponse(code = 200, message = "OK")
    @GetMapping("all")
    public ResponseEntity<List<DiagnosisDto>> getDiagnoses() {
        return ResponseEntity.ok(diagnosisDAO.getAllDiagnosisDto());
    }

    @ApiOperation(value = "Create new Diagnosis", response = SimpleResponse.class)
    @ApiResponse(code = 200, message = "OK")
    @PostMapping
    public ResponseEntity<SimpleResponse> insert(@ApiParam(value = "Diagnosis object", required = true) @RequestBody Diagnosis diagnosis) {
        Long id = diagnosisDAO.insert(diagnosis);
        return ResponseEntity.ok(SimpleResponse.of(HttpStatus.OK));
    }
}
