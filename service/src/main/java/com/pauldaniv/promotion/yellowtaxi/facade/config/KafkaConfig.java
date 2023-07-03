package com.pauldaniv.promotion.yellowtaxi.facade.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pauldaniv.promotion.yellowtaxi.facade.model.TripRequest;
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
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public KafkaTemplate<String, TripRequest> orderKafkaTemplate(final ObjectMapper objectMapper,
                                                                 final DefaultKafkaProducerFactory<String, TripRequest> producerFactory) {
        producerFactory.setValueSerializer(new JsonSerializer<>(objectMapper));

        return new KafkaTemplate<>(producerFactory);
    }
}
