package com.loginov.demo.model.dto;

import com.loginov.demo.model.Diagnosis;
import com.loginov.demo.model.Ward;
import org.springframework.lang.NonNull;

public class PersonDto {
    @NonNull
    private final String firstName;
    @NonNull
    private final String lastName;
    @NonNull
    private final String fatherName;

    @NonNull
    private final Ward ward;

    @NonNull
    private final Diagnosis diagnosis;

    public PersonDto(){
        this("", "", "", new Ward(), new Diagnosis());
    }

    public PersonDto(@NonNull final String firstName,
                     @NonNull final String lastName,
                     @NonNull final String fatherName,
                     @NonNull Ward ward,
                     @NonNull Diagnosis diagnosis) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.ward = ward;
        this.diagnosis = diagnosis;
    }

    @NonNull
    public Ward getWard() {
        return ward;
    }

    @NonNull
    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFatherName() {
        return fatherName;
    }
}
