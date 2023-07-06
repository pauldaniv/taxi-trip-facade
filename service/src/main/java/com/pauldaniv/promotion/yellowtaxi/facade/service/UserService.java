package com.pauldaniv.promotion.yellowtaxi.facade.service;

import com.pauldaniv.promotion.yellowtaxi.facade.model.User;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Getter
public class UserService {

    private Map<String, User> users = new HashMap<>();

    @Autowired
    public UserService() {
        users.put("pavlo.daniv@sombrainc.com", User.builder()
                .id("pdaniv")
                .email("pavlo.daniv@sombrainc.com")
                .password("$2a$10$l1h14dP0h47egIDrY9epEux/t1V0hcXT93AJBgl.P9Jtnbro7nziq")
                .build());
    }


}
