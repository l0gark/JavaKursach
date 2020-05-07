package com.loginov.demo.controller;

import com.loginov.demo.dao.ward.WardDAO;
import com.loginov.demo.model.SimpleResponse;
import com.loginov.demo.model.Ward;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@Api(value = "Ward")
@RequestMapping("ward")
@RestController
public class WardController {

    private final WardDAO wardDAO;

    @Autowired
    public WardController(@RequestBody final WardDAO wardDAO) {
        this.wardDAO = wardDAO;
    }

    @ApiOperation(value = "Return ward", response = Ward.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad request"),
    })
    @GetMapping("{id}")
    public ResponseEntity<Ward> getWardById(@PathVariable Long id) {
        try {
            final Ward ward = wardDAO.getWardById(id);
            return ResponseEntity.ok(ward);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @ApiOperation(value = "View list of all wards")
    @ApiResponse(code = 200, message = "OK")
    @GetMapping("all")
    public ResponseEntity<List<Ward>> getWards() {
        return ResponseEntity.ok(wardDAO.getAllWards());
    }

    @ApiOperation(value = "Create new ward", response = SimpleResponse.class)
    @ApiResponse(code = 200, message = "OK")
    @PostMapping
    public ResponseEntity<SimpleResponse> insert(@RequestBody Ward ward) {
        wardDAO.insert(ward);
        return ResponseEntity.ok(SimpleResponse.of(HttpStatus.OK));
    }
}
