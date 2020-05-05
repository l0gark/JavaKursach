package com.loginov.demo.model;

import org.springframework.lang.NonNull;

public class Person {
    @NonNull
    private final String firstName;
    @NonNull
    private final String lastName;
    @NonNull
    private final String fatherName;

    private final int wardId;
    private final int diagnosisId;


    public Person(@NonNull final String firstName,
                  @NonNull final String lastName,
                  @NonNull final String fatherName, int wardId, int diagnosisId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.wardId = wardId;
        this.diagnosisId = diagnosisId;
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

    public int getWardId() {
        return wardId;
    }

    public int getDiagnosisId() {
        return diagnosisId;
    }
}
