package com.loginov.demo.model;

import org.springframework.lang.NonNull;

public class Diagnosis {
    @NonNull
    private final String name;

    public Diagnosis() {
        this("");
    }

    public Diagnosis(@NonNull final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
