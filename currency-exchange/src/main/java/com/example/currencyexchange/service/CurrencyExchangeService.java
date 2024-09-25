package com.example.currencyexchange.service;

import com.example.currencyexchange.dto.CurrencyExchangeDto;
import com.example.currencyexchange.dto.MoneyPair;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CurrencyExchangeService {
    private final Map<MoneyPair, Double> exchangeRateMap = new HashMap<>();
    private final Environment env;

    {
        exchangeRateMap.put(new MoneyPair("USD", "VND"), 25_000d);
        exchangeRateMap.put(new MoneyPair("USD", "YEN"), 100d);
    }

    public CurrencyExchangeService(Environment env) {
        this.env = env;
    }

    public CurrencyExchangeDto getExchangeRate(String from, String to) {
        Double exchangeRate = exchangeRateMap.get(new MoneyPair(from, to));
        return CurrencyExchangeDto.builder()
                .from(from)
                .to(to)
                .exchangeRate(exchangeRate != null ? exchangeRate : -1d)
                .currencyExchangePort(env.getProperty("server.port"))
                .build();
    }
}
