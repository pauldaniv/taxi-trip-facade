package com.pauldaniv.promotion.yellowtaxi.facade.api;

import com.pauldaniv.promotion.yellowtaxi.facade.model.ResponseData;
import com.pauldaniv.promotion.yellowtaxi.facade.model.TotalsResponse;
import com.pauldaniv.promotion.yellowtaxi.facade.model.TripRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TaxiTripFacadeAPI {
    @POST("/v1/trips")
    Call<ResponseData> pushTaxiTrip(@Body TripRequest tripRequest);

    @GET("/v1/trips/totals")
    Call<TotalsResponse> getTotals(@Query("year") Integer year,
                                   @Query("month") Integer month,
                                   @Query("day") Integer day);
}
