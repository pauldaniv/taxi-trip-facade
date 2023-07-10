package com.pauldaniv.promotion.yellowtaxi.facade.test.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pauldaniv.promotion.yellowtaxi.facade.controller.TaxiTripController;
import com.pauldaniv.promotion.yellowtaxi.facade.model.TotalsResponse;
import com.pauldaniv.promotion.yellowtaxi.facade.model.TripRequest;
import com.pauldaniv.promotion.yellowtaxi.facade.service.StatsService;
import com.pauldaniv.promotion.yellowtaxi.facade.service.TaxiTripService;
import lombok.extern.slf4j.Slf4j;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Map;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
public class TaxiTripApiTest {

    private MockMvc mockMvc;
    @Mock
    private TaxiTripService producer;

    @Mock
    private StatsService statsService;

    @InjectMocks
    private TaxiTripController taxiTripController;

    @BeforeTest
    public void setup() {
        MockitoAnnotations.openMocks(this);
        //TODO: replacy with regular mockmvc test
        mockMvc = MockMvcBuilders.standaloneSetup(taxiTripController).build();
    }

    @Test
    public void postsTrip() throws Exception {
        final TripRequest requestBody = TripRequest.builder()
                .doLocationId(123)
                .tripDistance(123.0D)
                .build();

        mockMvc.perform(post("/v1/trips")
                        .content(new ObjectMapper().writeValueAsBytes(requestBody))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("ok")));

        verify(producer).pushTripToQueue(any());
    }

    @Test
    public void getsStatus() throws Exception {
        final TripRequest requestBody = TripRequest.builder()
                .doLocationId(123)
                .tripDistance(123.0D)
                .build();


        final LocalDate now = LocalDate.now();
        final int year = now.getYear();
        final int month = now.getMonthValue();
        final int dayOfMonth = now.getDayOfMonth();

        when(statsService.calculateTripTotals(year, month, dayOfMonth))
                .thenReturn(TotalsResponse.builder().total(new BigDecimal("142.2")).build());

        mockMvc.perform(get("/v1/trips/totals")
                        .param("year", String.valueOf(year))
                        .param("month", String.valueOf(month))
                        .param("day", String.valueOf(dayOfMonth))
                        .content(new ObjectMapper().writeValueAsBytes(requestBody))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("42")));

        verify(statsService).calculateTripTotals(year, month, dayOfMonth);
    }
}
