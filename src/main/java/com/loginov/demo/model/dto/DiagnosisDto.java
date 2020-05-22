package com.loginov.demo.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosisDto {
    @ApiModelProperty(notes = "Diagnosis name")
    @NonNull
    private String name;

    @ApiModelProperty(notes = "Persons count")
    @NonNull
    private Integer count;
}
