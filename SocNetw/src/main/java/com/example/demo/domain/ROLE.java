package com.example.demo.domain;

import org.springframework.security.core.GrantedAuthority;

public enum ROLE implements GrantedAuthority {
    USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
