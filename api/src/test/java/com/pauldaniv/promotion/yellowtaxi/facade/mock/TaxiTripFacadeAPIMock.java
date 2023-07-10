package com.pauldaniv.promotion.yellowtaxi.facade.mock;

import com.pauldaniv.promotion.yellowtaxi.facade.api.TaxiTripFacadeAPI;
import com.pauldaniv.promotion.yellowtaxi.facade.model.AuthRequest;
import com.pauldaniv.promotion.yellowtaxi.facade.model.AuthResponse;
import com.pauldaniv.promotion.yellowtaxi.facade.model.ResponseData;
import com.pauldaniv.promotion.yellowtaxi.facade.model.TotalsResponse;
import com.pauldaniv.promotion.yellowtaxi.facade.model.TripRequest;
import retrofit2.Call;

public class TaxiTripFacadeAPIMock implements TaxiTripFacadeAPI {

    @Override
    public Call<ResponseData> pushTaxiTrip(TripRequest tripRequest) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Call<TotalsResponse> getTotals(Integer year, Integer month, Integer day) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Call<AuthResponse> login(AuthRequest authRequest) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Call<AuthResponse> identity() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
