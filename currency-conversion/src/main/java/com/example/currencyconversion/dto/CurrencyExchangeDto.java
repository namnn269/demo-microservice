package com.example.currencyconversion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyExchangeDto {
    private String from;
    private String to;
    private double exchangeRate;
    private String currencyExchangePort;
}
