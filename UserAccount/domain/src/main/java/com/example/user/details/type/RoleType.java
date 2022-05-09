package com.example.user.details.type;

public enum RoleType {
    STUDENT("STUDENT"),
    TEACHER("TEACHER");

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

}
