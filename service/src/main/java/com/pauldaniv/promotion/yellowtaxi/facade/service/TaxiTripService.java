package com.pauldaniv.promotion.yellowtaxi.facade.service;

import com.pauldaniv.promotion.yellowtaxi.facade.model.TripRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaxiTripService {
    private final KafkaTemplate<String, TripRequest> template;

    @Transactional
    public void pushTripToQueue(final TripRequest data) {
        final int dropOffMonth = data.getTPepDropOffDatetime().getMonthValue();
        final int dropOffDay = data.getTPepDropOffDatetime().getDayOfMonth();
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
