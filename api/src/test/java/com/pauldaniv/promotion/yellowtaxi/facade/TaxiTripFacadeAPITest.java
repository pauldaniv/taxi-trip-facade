package com.pauldaniv.promotion.yellowtaxi.facade;

import com.pauldaniv.promotion.yellowtaxi.facade.api.TaxiTripFacadeAPI;
import com.pauldaniv.promotion.yellowtaxi.facade.model.ResponseData;
import com.pauldaniv.promotion.yellowtaxi.facade.model.TotalsResponse;
import com.pauldaniv.promotion.yellowtaxi.facade.model.TripRequest;
import lombok.extern.slf4j.Slf4j;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Slf4j
public class TaxiTripFacadeAPITest {

    @Mock
    private TaxiTripFacadeAPI taxiTripFacadeAPI;


    @BeforeTest
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void pushesTaxiTrip() throws IOException {
        final ResponseData body = new ResponseData();
        body.setMessage("test_ok");
        Call<ResponseData> mockResponseCall = mock(Call.class);

        when(mockResponseCall.execute())
                .thenReturn(Response.success(body));

        when(taxiTripFacadeAPI.pushTaxiTrip(ArgumentMatchers.any()))
                .thenReturn(mockResponseCall);

        final TripRequest tripRequest = new TripRequest();
        final Response<ResponseData> response = taxiTripFacadeAPI.pushTaxiTrip(tripRequest).execute();

        assertThat(response.body()).isNotNull();
        assertThat(response.body().getMessage()).isEqualTo("test_ok");
        verify(taxiTripFacadeAPI, atMostOnce()).pushTaxiTrip(tripRequest);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void getsTotal() throws IOException {
        final TotalsResponse totalsResponse = new TotalsResponse();
        totalsResponse.setTotal(new BigDecimal("123.4"));
        totalsResponse.setDate(LocalDate.of(2023, 6, 18));

        Call<TotalsResponse> mockResponseCall = mock(Call.class);

        when(mockResponseCall.execute())
                .thenReturn(Response.success(totalsResponse));
        when(taxiTripFacadeAPI.getTotals(anyInt(), anyInt(), anyInt()))
                .thenReturn(mockResponseCall);

        final TripRequest tripRequest = new TripRequest();
        final Response<TotalsResponse> response = taxiTripFacadeAPI.getTotals(2023, 6, 18).execute();
        final TotalsResponse responseBody = response.body();
        assertThat(responseBody).isNotNull();
        assertThat(responseBody.getTotal()).isEqualTo("123.4");
        assertThat(responseBody.getDate()).isEqualTo("2023-06-18");
        verify(taxiTripFacadeAPI, atMostOnce()).pushTaxiTrip(tripRequest);
    }

    @AfterTest
    public void teardown() {
        reset(taxiTripFacadeAPI);
    }
}
