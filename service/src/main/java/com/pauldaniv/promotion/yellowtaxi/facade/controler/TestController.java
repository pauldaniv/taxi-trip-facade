package com.pauldaniv.promotion.yellowtaxi.facade.controler;

import com.pauldaniv.promotion.yellowtaxi.model.TripResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class TestController {

    @GetMapping("/trips")
    public List<TripResponse> getResponse() {
        return List.of(TripResponse.builder()
                .id("test")
                .build());
    }
}
