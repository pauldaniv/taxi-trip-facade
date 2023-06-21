package com.pauldaniv.promotion.yellowtaxi.facade.test.service;

import com.pauldaniv.promotion.yellowtaxi.facade.model.TripRequest;
import com.pauldaniv.promotion.yellowtaxi.facade.service.TaxiTripService;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class TaxiTripServiceTest {

    @Mock
    private KafkaTemplate<String, TripRequest> template;

    private TaxiTripService taxiTripService;

    @BeforeTest
    public void setup() {
        MockitoAnnotations.openMocks(this);
        taxiTripService = new TaxiTripService(template);
    }

    @Test
    void pushesTripToQueue() {
        final TripRequest value = TripRequest.builder()
                .tPepDropOffDatetime(LocalDateTime.now())
                .build();

        final var sendResultCompletableFuture = new CompletableFuture<SendResult<String, TripRequest>>();

        final var producerRecord = spy(new ProducerRecord<String, TripRequest>("taxi-trips", value));

        final var sendResult = mock(SendResult.class);

        when(sendResult.getProducerRecord())
                .thenReturn(producerRecord);

        when(producerRecord.value())
                .thenReturn(value);

        final var sendFuture = mock(CompletableFuture.class);

        final var thenAccept = mock(CompletableFuture.class);
        final var exceptionally = spy(CompletableFuture.class);

        Mockito.when(template.send(eq("taxi-trips"), any()))
                .thenReturn(sendResultCompletableFuture);

        when(sendFuture.thenAccept(any())).
                thenReturn(thenAccept);

        when(thenAccept.exceptionally(any()))
                .thenReturn(exceptionally);


        final TripRequest tripRequest = TripRequest.builder()
                .tPepDropOffDatetime(LocalDateTime.now())
                .build();
        taxiTripService.pushTripToQueue(tripRequest);
        sendResultCompletableFuture.complete(sendResult);

        Mockito.verify(template).send(eq("taxi-trips"), any());
    }

    @Test
    void pushesTripToQueueExceptionally() {
        final TripRequest value = TripRequest.builder()
                .tPepDropOffDatetime(LocalDateTime.now())
                .build();

        final var sendResultCompletableFuture = new CompletableFuture<SendResult<String, TripRequest>>();

        final var producerRecord = spy(new ProducerRecord<String, TripRequest>("taxi-trips", value));

        final var sendResult = mock(SendResult.class);

        when(sendResult.getProducerRecord())
                .thenReturn(producerRecord);

        when(producerRecord.value())
                .thenReturn(value);

        final var sendFuture = mock(CompletableFuture.class);

        final var thenAccept = mock(CompletableFuture.class);
        final var exceptionally = spy(CompletableFuture.class);

        Mockito.when(template.send(eq("taxi-trips"), any()))
                .thenReturn(sendResultCompletableFuture);

        when(sendFuture.thenAccept(any())).
                thenReturn(thenAccept);

        when(thenAccept.exceptionally(any()))
                .thenReturn(exceptionally);


        final TripRequest tripRequest = TripRequest.builder()
                .tPepDropOffDatetime(LocalDateTime.now())
                .build();
        taxiTripService.pushTripToQueue(tripRequest);
        sendResultCompletableFuture.completeExceptionally(new RuntimeException("test exception"));

        Mockito.verify(template).send(eq("taxi-trips"), any());
    }

    @AfterMethod
    public void teardown() {
        reset(template);
    }
}
