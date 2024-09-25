package com.example.currencyconversion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencyConversionDto {
    private String from;
    private String to;
    private double originalAmount;
    private double exchangedAmount;
    private double exchangeRate;
    private String currencyExchangePort;
    private String currencyConversionPort;
}
