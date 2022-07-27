package com.example.eduproject.security;

public enum Permission {
    READ("read"),
    WRITE("write"),
    CALCULATE("calculate");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
