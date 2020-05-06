package com.loginov.demo.model;

import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "ward")
public class Ward {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "name", nullable = false)
    private final String name;

    @NonNull
    @Column(name = "max_count", nullable = false)
    private final Integer maxCount;

    public Ward() {
        this("FAILED", -1);
    }

    public Ward(@NonNull final String name,
                final int maxCount) {
        this.name = name;
        this.maxCount = maxCount;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMaxCount() {
        return maxCount;
    }
}
