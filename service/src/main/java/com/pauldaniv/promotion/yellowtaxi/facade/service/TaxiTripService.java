package com.pauldaniv.promotion.yellowtaxi.facade.service;

import com.pauldaniv.promotion.yellowtaxi.model.TripRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaxiTripService {
    private final KafkaTemplate<String, Object> template;

    @Transactional
    public void pushTripToQueue(TripRequest data) {
        final int dropOffMonth = data.getTPepDropOffDatetime().getMonthValue();
        final int dropOffDay = data.getTPepDropOffDatetime().getDayOfMonth();
        data.setDropOffMonth(dropOffMonth);
        data.setDropOffDay(dropOffDay);
        template.send("testTopic", data).thenAccept(it -> {
            log.info("msg=message_sent value={}", it.getProducerRecord().value());
        }).exceptionally(ex -> {
            log.error("msg=error_occurred");
            return null;
        });
    }
}
