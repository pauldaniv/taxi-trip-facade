package com.pauldaniv.promotion.yellowtaxi.facade.test.service;

import com.pauldaniv.promotion.yellowtaxi.facade.service.StatsService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import redis.clients.jedis.JedisPooled;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StatsServiceTest {

    @Mock
    private JedisPooled jedisPooled;

    private StatsService statsService;

    @BeforeTest
    public void setup() {
        MockitoAnnotations.openMocks(this);
        statsService = new StatsService(jedisPooled);
    }

    @Test
    void getsStats() {
        assertThat(statsService.calculateTripTotals(2018, 2, 2)).isNotNull();
    }

    @Test
    void getsStatsForMonth() {
        assertThat(statsService.calculateTripTotals(2018, 2, null)).isNotNull();
    }

    @Test
    void getsStatsFromRedisByMonth() {
        when(jedisPooled.get("2")).thenReturn("1233");
        when(jedisPooled.get("3")).thenReturn(null);
        assertThat(statsService.calculateTripTotals(2018, 2, null).getTotal()).isEqualTo("123");
        assertThat(statsService.calculateTripTotals(2018, 3, null).getTotal()).isZero();
        verify(jedisPooled).get("2");
        verify(jedisPooled).get("3");
    }

    @Test
    void getsStatsFromRedisByDay() {
        when(jedisPooled.get("2/3")).thenReturn("123");
        when(jedisPooled.get("3/3")).thenReturn(null);
        assertThat(statsService.calculateTripTotals(2018, 2, 3).getTotal()).isEqualTo("123");
        assertThat(statsService.calculateTripTotals(2018, 3, 3).getTotal()).isZero();
        verify(jedisPooled).get("2/3");
        verify(jedisPooled).get("3/3");
    }

    @AfterMethod
    public void teardown() {
        reset(jedisPooled);
    }
}
