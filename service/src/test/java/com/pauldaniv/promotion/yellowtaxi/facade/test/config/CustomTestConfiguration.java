package com.pauldaniv.promotion.yellowtaxi.facade.test.config;

import com.pauldaniv.promotion.yellowtaxi.facade.service.StatsService;
import com.pauldaniv.promotion.yellowtaxi.facade.service.TaxiTripService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

import static org.mockito.Mockito.mock;

@EnableWebMvc
@Configuration
@ComponentScan("com.pauldaniv.promotion.yellowtaxi.facade")
public class CustomTestConfiguration {

//    @Primary
//    @Bean
//    public TaxiTripService mockTaxiTripService() {
//        return mock(TaxiTripService.class);
//    }
//
//    @Primary
//    @Bean
//    public StatsService mockStatsService() {
//        return mock(StatsService.class);
//    }
//
//    @Primary
//    @Bean
//    public KafkaTemplate mockKafkaTemplate() {
//        return mock(KafkaTemplate.class);
//    }
//
//    @Primary
//    @Bean
//    public DataSource mockDataSource() {
//        return mock(DataSource.class);
//    }
}
