package com.pauldaniv.promotion.yellowtaxi.facade.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pauldaniv.promotion.yellowtaxi.model.TaxiTrip;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic testTopic() {
        return TopicBuilder.name("taxi-trips")
                .partitions(2)
                .replicas(3)
                .build();
    }

    @Bean
    public KafkaTemplate<String, TaxiTrip> orderKafkaTemplate(final ObjectMapper objectMapper,
                                                              final DefaultKafkaProducerFactory<String, TaxiTrip> producerFactory) {
        producerFactory.setValueSerializer(new JsonSerializer<>(objectMapper));

        return new KafkaTemplate<>(producerFactory);
    }
}
