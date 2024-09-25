package com.example.currencyconversion.service;

import com.example.currencyconversion.dto.CurrencyConversionDto;
import com.example.currencyconversion.dto.CurrencyExchangeDto;
import com.example.currencyconversion.openfeign.CurrencyExchangeServiceProxy;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyConversionService {

    private final RestTemplate restTemplate;
    private final CurrencyExchangeServiceProxy currencyExchangeServiceProxy;
    private final Environment env;
    @Value("${currency.exchange.url}")
    private String currencyExchangeUrl;

    //    @Retry(name = "convertCurrency", fallbackMethod = "getWithRestTemplateFallback")
    @CircuitBreaker(name = "currencyCircuit", fallbackMethod = "getWithRestTemplateFallback")
    public CurrencyConversionDto getWithRestTemplate(String from,
                                                     String to,
                                                     double originalAmount) {
        String url = currencyExchangeUrl + "/currency-exchange/from/{from}/to/{to}";
        log.info("CurrencyConversionService::Log BEFORE call restTemplate api {}", url);
        ResponseEntity<CurrencyExchangeDto> response = restTemplate.getForEntity(
                url,
                CurrencyExchangeDto.class,
                from, to);
        log.info("CurrencyConversionService::Log AFTER call restTemplate api");
        CurrencyExchangeDto responseDto = response.getBody();
        assert responseDto != null;
        return CurrencyConversionDto.builder()
                .from(from)
                .to(to)
                .originalAmount(originalAmount)
                .exchangedAmount(responseDto.getExchangeRate() * originalAmount)
                .exchangeRate(responseDto.getExchangeRate())
                .currencyExchangePort(responseDto.getCurrencyExchangePort())
                .currencyConversionPort(env.getProperty("server.port"))
                .build();
    }

    public CurrencyConversionDto getWithOpenFeign(String from,
                                                  String to,
                                                  double originalAmount) {
        log.info("CurrencyConversionService::Log BEFORE call api by openFeign");
        ResponseEntity<CurrencyExchangeDto> response = currencyExchangeServiceProxy.getCurrencyExchangeRate(from, to);
        log.info("CurrencyConversionService::Log AFTER call api by openFeign");
        CurrencyExchangeDto responseDto = response.getBody();
        assert responseDto != null;
        return CurrencyConversionDto.builder()
                .from(from)
                .to(to)
                .originalAmount(originalAmount)
                .exchangedAmount(responseDto.getExchangeRate() * originalAmount)
                .exchangeRate(responseDto.getExchangeRate())
                .currencyExchangePort(responseDto.getCurrencyExchangePort())
                .currencyConversionPort(env.getProperty("server.port"))
                .build();
    }

    public CurrencyConversionDto getWithRestTemplateFallback(Exception ex) {
        log.error("ERROR::CurrencyConversionService.getWithRestTemplateFallback()");
        return CurrencyConversionDto.builder()
                .from("Error")
                .to("Error")
                .build();
    }

}

