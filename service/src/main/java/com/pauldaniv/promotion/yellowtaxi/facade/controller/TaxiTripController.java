package com.pauldaniv.promotion.yellowtaxi.facade.controller;

import com.pauldaniv.promotion.yellowtaxi.facade.model.ResponseData;
import com.pauldaniv.promotion.yellowtaxi.facade.model.TotalsResponse;
import com.pauldaniv.promotion.yellowtaxi.facade.model.TripRequest;
import com.pauldaniv.promotion.yellowtaxi.facade.service.StatsService;
import com.pauldaniv.promotion.yellowtaxi.facade.service.TaxiTripService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class TaxiTripController {

    private final TaxiTripService producer;
    private final StatsService statsService;

    @PostMapping("/trips")
    public ResponseEntity<ResponseData> getResponse(@RequestBody TripRequest tripRequest) {
        producer.pushTripToQueue(tripRequest);
        final ResponseData responseData = new ResponseData();
        return ResponseEntity.ok(ResponseData.builder().message("ok").build());
    }

    @GetMapping("/trips/totals")
    public TotalsResponse stats(@RequestParam("month") final Integer month, @RequestParam("day") final Integer day) {
        return statsService.calculateTripTotals(month, day);
    }
}
