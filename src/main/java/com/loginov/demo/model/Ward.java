package com.loginov.demo.model;

import org.springframework.lang.NonNull;

public class Ward {
    @NonNull
    private final String name;
    private final int maxCount;

    public Ward() {
        this("FAILED", -1);
    }

    public Ward(@NonNull final String name,
                final int maxCount) {
        this.name = name;
        this.maxCount = maxCount;
    }

    public String getName() {
        return name;
    }

    public int getMaxCount() {
        return maxCount;
    }
}
