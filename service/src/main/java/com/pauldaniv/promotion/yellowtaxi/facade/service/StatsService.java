package com.pauldaniv.promotion.yellowtaxi.facade.service;


import com.pauldaniv.promotion.yellowtaxi.facade.model.TotalsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPooled;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatsService {

    private final JedisPooled jedisPooled;

    public TotalsResponse calculateTripTotals(final Integer month, final Integer day) {
        final String val = jedisPooled.get(String.format("%s/%s", month, day));
        final BigDecimal total = Optional.ofNullable(val).map(BigDecimal::new).orElse(BigDecimal.ZERO);
        return TotalsResponse.builder()
                .total(total)
                .date(LocalDate.of(2018, month, day))
                .build();
    }
}
