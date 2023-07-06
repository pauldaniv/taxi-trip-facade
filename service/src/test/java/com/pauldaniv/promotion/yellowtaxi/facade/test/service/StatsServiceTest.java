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
        assertThat(statsService.calculateTripTotals(2, 2)).isNotNull();
    }

    @AfterMethod
    public void teardown() {
        reset(jedisPooled);
    }
}
