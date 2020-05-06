package com.loginov.demo.controller;

import com.loginov.demo.dao.ward.WardDAO;
import com.loginov.demo.model.Ward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WardController {

    private final WardDAO wardDAO;

    @Autowired
    public WardController(@RequestBody final WardDAO wardDAO) {
        this.wardDAO = wardDAO;
    }

    @GetMapping("/ward")
    public @ResponseBody
    Ward getWardById(@RequestParam Long id) {
        return wardDAO.getWardById(id);
    }

    @GetMapping("/wards")
    public @ResponseBody
    List<Ward> getWards() {
        return wardDAO.getAllWards();
    }

    @PostMapping("/ward")
    public @ResponseBody
    ResponseEntity<HttpStatus> insert(@RequestBody Ward ward) {
        wardDAO.insert(ward);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
