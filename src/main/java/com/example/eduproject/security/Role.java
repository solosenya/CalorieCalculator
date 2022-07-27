package com.example.eduproject.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    USER(Set.of(Permission.READ, Permission.CALCULATE)),
    ADMIN(Set.of(Permission.READ, Permission.CALCULATE, Permission.WRITE));

    private final Set<Permission> permissions;

     Role(Set<Permission> permissions) {
         this.permissions = permissions;
     }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
         return getPermissions().stream()
                 .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                 .collect(Collectors.toSet());
    }
}
