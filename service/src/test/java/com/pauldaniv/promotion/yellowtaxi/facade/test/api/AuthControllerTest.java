package com.pauldaniv.promotion.yellowtaxi.facade.test.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pauldaniv.promotion.yellowtaxi.facade.config.security.JwtUtils;
import com.pauldaniv.promotion.yellowtaxi.facade.controller.AuthController;
import com.pauldaniv.promotion.yellowtaxi.facade.model.AuthRequest;
import com.pauldaniv.promotion.yellowtaxi.facade.model.User;
import com.pauldaniv.promotion.yellowtaxi.facade.model.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Set;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
public class AuthControllerTest {

    private MockMvc mockMvc;
    @Mock
    private AuthenticationManager authManager;

    @Mock
    private JwtUtils jwtUtils;

    @InjectMocks
    private AuthController authController;

    @BeforeTest
    public void setup() {
        MockitoAnnotations.openMocks(this);
        //TODO: replacy with regular mockmvc test
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    public void logsInSuccessfully() throws Exception {
        final AuthRequest requestBody = AuthRequest.builder()
                .email("pavlo.daniv@sombrainc.com")
                .password("test")
                .build();
        when(jwtUtils.generateAccessToken(any())).thenReturn("test.token");
        final Authentication mockAuth = mock(Authentication.class);
        when(mockAuth.getPrincipal()).thenReturn(new User());
        when(authManager.authenticate(any())).thenReturn(mockAuth);
        mockMvc.perform(post("/v1/auth/login")
                        .content(new ObjectMapper().writeValueAsBytes(requestBody))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("test.token")));
    }

    @Test
    public void logsInUnsuccessfully() throws Exception {
        final AuthRequest requestBody = AuthRequest.builder()
                .email("pavlo.daniv@sombrainc.com")
                .password("test")
                .build();
        when(authManager.authenticate(any())).thenThrow(new BadCredentialsException("test"));
        mockMvc.perform(post("/v1/auth/login")
                        .content(new ObjectMapper().writeValueAsBytes(requestBody))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(emptyString()));
    }

    @Test
    public void getsIdentity() throws Exception {
        mockMvc.perform(get("/v1/identity")
                        .header("Authorization", "test_token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .principal(User.builder()
                                .authorities(Set.of(UserRole.USER))
                                .email("pavlo.daniv+driver@sombrainc.com")
                                .build()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("test_token")))
                .andExpect(content().string(containsString("pavlo.daniv+driver@sombrainc.com")));
    }
}
