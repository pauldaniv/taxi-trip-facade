package com.pauldaniv.promotion.yellowtaxi.facade.service;

import com.pauldaniv.promotion.yellowtaxi.model.TotalsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPooled;

import java.math.BigDecimal;
import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatsService {
    private final JedisPooled jedisPooled;
    //    @Cacheable("itemCache")
    public TotalsResponse calculateTripTotals(final Integer year, final Integer month, final Integer day) {
        if (day != null && month != null) {
            jedisPooled.get(String.format("%s_%s_%s", year, month, day));
        } else {

        }
        return TotalsResponse.builder()
                .total(new BigDecimal("142.12"))
                .date(LocalDate.of(year, month, day))
                .build();
    }
}
