package com.loginov.demo.controller;

import com.loginov.demo.dao.ward.WardDAO;
import com.loginov.demo.model.SimpleResponse;
import com.loginov.demo.model.Ward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("ward")
@RestController
public class WardController {

    private final WardDAO wardDAO;

    @Autowired
    public WardController(@RequestBody final WardDAO wardDAO) {
        this.wardDAO = wardDAO;
    }

    @GetMapping("{id}")
    public ResponseEntity<Ward> getWardById(@PathVariable Long id) {
        try {
            final Ward ward = wardDAO.getWardById(id);
            return ResponseEntity.ok(ward);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("all")
    public ResponseEntity<List<Ward>> getWards() {
        return ResponseEntity.ok(wardDAO.getAllWards());
    }

    @PostMapping
    public ResponseEntity<SimpleResponse> insert(@RequestBody Ward ward) {
        wardDAO.insert(ward);
        return ResponseEntity.ok(SimpleResponse.of(HttpStatus.OK));
    }
}
