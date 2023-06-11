package com.pauldaniv.promotion.yellowtaxi.facade.controler;

import com.pauldaniv.promotion.yellowtaxi.facade.service.StatsService;
import com.pauldaniv.promotion.yellowtaxi.facade.service.TaxiTripService;
import com.pauldaniv.promotion.yellowtaxi.model.ResponseData;
import com.pauldaniv.promotion.yellowtaxi.model.TripRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class TripController {
    private final TaxiTripService producer;
    private final StatsService statsService;

    @PostMapping("/trips")
    public ResponseEntity<ResponseData> getResponse(@RequestBody TripRequest tripRequest) {
        producer.pushTripToQueue(tripRequest);
        final ResponseData responseData = new ResponseData();
        return ResponseEntity.ok(ResponseData.builder().message("ok").build());
    }

    @GetMapping("/stats")
    public String stats() {
        return statsService.data();
    }
}
