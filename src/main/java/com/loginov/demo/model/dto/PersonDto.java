package com.loginov.demo.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@ApiModel(description = "Person data transfer object")
@Data
@NoArgsConstructor
public class PersonDto {
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

    @ApiModelProperty(notes = "Diagnosis id that contained in table diagnosis", required = true)
    @NonNull
    private Long diagnosisId;

}
