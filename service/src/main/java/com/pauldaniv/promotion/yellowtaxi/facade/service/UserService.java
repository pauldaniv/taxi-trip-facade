package com.pauldaniv.promotion.yellowtaxi.facade.service;

import com.pauldaniv.promotion.yellowtaxi.facade.model.User;
import com.pauldaniv.promotion.yellowtaxi.facade.model.UserRole;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
@Getter
public class UserService {

    private Map<String, User> users = new HashMap<>();

    public UserService() {
        users.put("pavlo.daniv@sombrainc.com", User.builder()
                .id("pdaniv")
                .email("pavlo.daniv@sombrainc.com")
                .password("$2a$10$l1h14dP0h47egIDrY9epEux/t1V0hcXT93AJBgl.P9Jtnbro7nziq")
                .authorities(Set.of(UserRole.ADMIN, UserRole.ANALYST))
                .build());
        users.put("pavlo.daniv+ba@sombrainc.com", User.builder()
                .id("ba")
                .email("pavlo.daniv+ba@sombrainc.com")
                .password("$2a$10$l1h14dP0h47egIDrY9epEux/t1V0hcXT93AJBgl.P9Jtnbro7nziq")
                .authorities(Set.of(UserRole.ANALYST))
                .build());
        users.put("pavlo.daniv+us@sombrainc.com", User.builder()
                .id("ro-user")
                .email("pavlo.daniv+us@sombrainc.com")
                .password("$2a$10$l1h14dP0h47egIDrY9epEux/t1V0hcXT93AJBgl.P9Jtnbro7nziq")
                .authorities(Set.of(UserRole.USER))
                .build());
    }
}
