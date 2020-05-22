package com.loginov.demo.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@ApiModel(description = "Person data transfer object")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonCreateDto {
    @ApiModelProperty(notes = "First name", required = true)
    @NonNull
    private String firstName;

    @ApiModelProperty(notes = "Last name", required = true)
    @NonNull
    private String lastName;

    @ApiModelProperty(notes = "Father name", required = true)
    @NonNull
    private String fatherName;

    @ApiModelProperty(notes = "Ward id that contained in table ward", required = true)
    @NonNull
    private Long wardId;

    @ApiModelProperty(notes = "If diagnosis is not exist then create new diagnosis else user old", required = true)
    @NonNull
    private String diagnosisName;
}
