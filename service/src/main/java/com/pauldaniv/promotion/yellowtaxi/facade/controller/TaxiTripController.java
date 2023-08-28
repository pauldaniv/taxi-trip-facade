package com.pauldaniv.promotion.yellowtaxi.facade.controller;

import com.pauldaniv.promotion.yellowtaxi.facade.model.ResponseData;
import com.pauldaniv.promotion.yellowtaxi.facade.model.TotalsResponse;
import com.pauldaniv.promotion.yellowtaxi.facade.model.User;
import com.pauldaniv.promotion.yellowtaxi.facade.service.StatsService;
import com.pauldaniv.promotion.yellowtaxi.facade.service.TaxiTripService;

import com.pauldaniv.promotion.yellowtaxi.model.TaxiTrip;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class TaxiTripController {

    private final TaxiTripService producer;
    private final StatsService statsService;

    @PostMapping("/trips")
    public ResponseEntity<ResponseData> getResponse(@AuthenticationPrincipal final Principal principal,
                                                    @RequestBody TaxiTrip tripRequest) {
        log.info("Got event from user: {}", principal);
        producer.pushTripToQueue(tripRequest);
        return ResponseEntity.ok(ResponseData.builder().message("ok").build());
    }

    @GetMapping("/trips/totals")
    public TotalsResponse stats(

            @RequestParam("year") final Integer year,
            @RequestParam("month") final Integer month,
            @RequestParam(value = "day") final Integer day) {
        return statsService.calculateTripTotals(year, month, day);
    }
}
