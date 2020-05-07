package com.loginov.demo.model.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    private static final String ROLE_USER_NAME = "ROLE_USER";
    private static final Role ROLE_USER = new Role(1L, ROLE_USER_NAME);

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    public Role(final Long id) {
        this.id = id;
    }

    public static Role user() {
        return ROLE_USER;
    }

    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    @Override
    public String getAuthority() {
        return name;
    }
}
