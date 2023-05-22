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
        template.send("testTopic", data).thenAccept(it -> {
            log.info("msg=message_sent");
        }).exceptionally(ex -> {
            log.error("msg=error_occurred");
            return null;
        });
    }
}
