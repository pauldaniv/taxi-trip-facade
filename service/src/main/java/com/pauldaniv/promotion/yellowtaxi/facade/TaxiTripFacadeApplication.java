package com.pauldaniv.promotion.yellowtaxi.facade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TaxiTripFacadeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaxiTripFacadeApplication.class, args);
	}

}
