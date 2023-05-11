package com.pauldaniv.promotion.yellowtaxi.facade.controler;

import com.pauldaniv.promotion.yellowtaxi.facade.service.StatsService;
import com.pauldaniv.promotion.yellowtaxi.facade.service.TestProducer;
import com.pauldaniv.promotion.yellowtaxi.model.TripRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class TestController {
    private final TestProducer producer;
    private final StatsService statsService;

    @PostMapping("/trips")
    public TripRequest getResponse(@RequestBody TripRequest tripRequest) {
        producer.push(tripRequest);
        return tripRequest;
    }

    @GetMapping("/stats")
    public String stats() {
        return statsService.data();
    }
}
