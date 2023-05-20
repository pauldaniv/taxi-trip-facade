package com.pauldaniv.promotion.yellowtaxi.facade.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
// for some reason when test is loaded, it's trying to find database
// which is not used for this service, so we disable it as following
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
class TaxiTripFacadeApplicationTests {

	@Test
	void contextLoads() {
	}

}
