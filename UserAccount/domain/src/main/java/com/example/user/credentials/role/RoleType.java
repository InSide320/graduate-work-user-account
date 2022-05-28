package com.example.user.credentials.role;

import org.springframework.security.core.GrantedAuthority;

public enum RoleType implements GrantedAuthority {
    STUDENT("STUDENT"),
    TEACHER("TEACHER"),
    ADMIN("ADMIN"),
    SUPER_ADMIN("SUPER_ADMIN");

    private final String role;

    RoleType(final String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "RoleType{"
                + "role='" + role + '\''
                + '}';
    }

    @Override
    public String getAuthority() {
        return name();
    }
}
