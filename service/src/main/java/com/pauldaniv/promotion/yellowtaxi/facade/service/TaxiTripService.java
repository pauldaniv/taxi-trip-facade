package com.pauldaniv.promotion.yellowtaxi.facade.service;

import com.pauldaniv.promotion.yellowtaxi.model.TaxiTrip;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaxiTripService {
    private final KafkaTemplate<String, TaxiTrip> template;

    @Transactional
    public void pushTripToQueue(final TaxiTrip data) {
        final LocalDateTime dropOffDatetime = LocalDateTime.from(data.getTPepDropOffDatetime());
        final int dropOffYear = dropOffDatetime.getYear();
        final int dropOffMonth = dropOffDatetime.getMonthValue();
        final int dropOffDay = dropOffDatetime.getDayOfMonth();
        data.setDropOffYear(dropOffYear);
        data.setDropOffMonth(dropOffMonth);
        data.setDropOffDay(dropOffDay);
        template.send("taxi-trips", data).thenAccept(it -> {
            log.info("msg=message_sent value={}", it.getProducerRecord().value());
        }).exceptionally(d -> {
            log.error("msg=error_occurred errorMessage={}", d.getMessage(), d);
            return null;
        });
    }
}
