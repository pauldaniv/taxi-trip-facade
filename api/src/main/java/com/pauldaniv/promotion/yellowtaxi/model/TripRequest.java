package com.pauldaniv.promotion.yellowtaxi.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TripRequest {
    private Long vendorId;
    private Long rateCodeId;
    private Long paymentTypeId;
    private LocalDateTime tPepPickupDatetime;
    private LocalDateTime tPepDropOffDatetime;
    private Integer dropOffMonth;
    private Integer dropOffDay;
    private Integer passengerCount;
    private Double tripDistance;
    private Integer puLocationId;
    private Integer doLocationId;
    private Boolean storeAndFwdFlag;
    private BigDecimal fareAmount;
    private BigDecimal extra;
    private BigDecimal mtaTax;
    private BigDecimal improvementSurcharge;
    private BigDecimal tipAmount;
    private BigDecimal tollsAmount;
    private BigDecimal totalAmount;
}
