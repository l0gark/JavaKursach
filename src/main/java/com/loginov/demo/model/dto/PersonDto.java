package com.loginov.demo.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Data
@NoArgsConstructor
public class PersonDto {
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String fatherName;

    @NonNull
    private Long wardId;

    @NonNull
    private Long diagnosisId;

}
