package com.pauldaniv.promotion.yellowtaxi.facade;

import com.pauldaniv.promotion.yellowtaxi.model.ResponseData;
import com.pauldaniv.promotion.yellowtaxi.model.TripRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TaxiTripFacadeAPI {
    @POST("/v1/trips")
    Call<ResponseData> pushTaxiTrip(@Body TripRequest tripRequest);
}
