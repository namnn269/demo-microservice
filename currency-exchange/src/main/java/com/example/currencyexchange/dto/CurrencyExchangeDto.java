package com.example.currencyexchange.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencyExchangeDto {
    private String from;
    private String to;
    private double exchangeRate;
    private String currencyExchangePort;
}
