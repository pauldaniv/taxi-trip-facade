package com.pauldaniv.promotion.yellowtaxi.facade.service;


import com.pauldaniv.promotion.yellowtaxi.facade.model.TotalsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPooled;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatsService {

    private final JedisPooled jedisPooled;

    public TotalsResponse calculateTripTotals(final Integer year,
                                              final Integer month,
                                              final Integer day) {
        String key;
        String date;
        if (day == -1) {
            key = String.format("%s", month);
            date = LocalDate.of(year, month, 1).format(DateTimeFormatter.ofPattern("yyyy-MM"));
        } else {
            key = String.format("%s/%s", month, day);
            date = LocalDate.of(year, month, day).toString();
        }
        final String val = jedisPooled.get(key);
        final BigDecimal total = Optional.ofNullable(val).map(BigDecimal::new).orElse(BigDecimal.ZERO);

        return TotalsResponse.builder()
                .total(total)
                .date(date)
                .build();
    }
}
