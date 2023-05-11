package com.pauldaniv.promotion.yellowtaxi.facade.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class StatsService {

    @Cacheable("itemCache")
    public String data() {
        return String.valueOf(System.currentTimeMillis());
    }
}
