package com.pauldaniv.promotion.yellowtaxi.facade.controler;

import com.pauldaniv.promotion.yellowtaxi.facade.service.TestProducer;
import com.pauldaniv.promotion.yellowtaxi.model.TripRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class TestController {
    private final TestProducer producer;

    @PostMapping("/trips")
    public TripRequest getResponse(@RequestBody TripRequest tripRequest) {
        producer.push(tripRequest);
        return tripRequest;
    }
}
