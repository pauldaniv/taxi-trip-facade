package com.pauldaniv.promotion.yellowtaxi.facade.controller;

import com.pauldaniv.promotion.yellowtaxi.facade.config.security.JwtUtils;
import com.pauldaniv.promotion.yellowtaxi.facade.model.AuthRequest;
import com.pauldaniv.promotion.yellowtaxi.facade.model.AuthResponse;
import com.pauldaniv.promotion.yellowtaxi.facade.model.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtils jwtUtils;

    @PostMapping("/auth/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest request) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword())
            );

            User user = (User) authentication.getPrincipal();
            String accessToken = jwtUtils.generateAccessToken(user);
            AuthResponse response = new AuthResponse(user.getEmail(), accessToken);

            return ResponseEntity.ok().body(response);

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/identity")
    public ResponseEntity<AuthResponse> home(@AuthenticationPrincipal User principal,
                                             @RequestHeader(HttpHeaders.AUTHORIZATION) String bearerToken) {
        return ResponseEntity.ok(AuthResponse.builder()
                        .email(principal.getEmail())
                        .accessToken(bearerToken)
                .build());
    }
}
