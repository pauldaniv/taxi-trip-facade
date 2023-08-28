package com.pauldaniv.promotion.yellowtaxi.facade.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    @NotNull
    private String email;
     
    @NotNull
    private String password;
}
