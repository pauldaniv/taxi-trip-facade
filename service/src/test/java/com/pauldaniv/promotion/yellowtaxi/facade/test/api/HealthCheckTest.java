package com.pauldaniv.promotion.yellowtaxi.facade.test.api;

import com.pauldaniv.promotion.yellowtaxi.facade.controller.HealthCheck;
import lombok.extern.slf4j.Slf4j;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.containsString;

@Slf4j
public class HealthCheckTest {

    private MockMvc mockMvc;

    @InjectMocks
    private HealthCheck healthCheck;

    @BeforeTest
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(healthCheck).build();
    }

    @Test
    public void testApi() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/health_check"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString("ok")));
    }
}
