package com.pauldaniv.promotion.yellowtaxi.facade.model;

import lombok.Data;
import org.jetbrains.annotations.NotNull;


@Data
public class AuthRequest {
    @NotNull
    private String email;
     
    @NotNull
    private String password;
 
    // getters and setters are not shown...
}
