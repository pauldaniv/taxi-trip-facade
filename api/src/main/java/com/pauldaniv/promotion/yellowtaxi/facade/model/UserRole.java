package com.pauldaniv.promotion.yellowtaxi.facade.model;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    USER,   ADMIN, ANALYST;

    @Override
    public String getAuthority() {
        return name();
    }
}
