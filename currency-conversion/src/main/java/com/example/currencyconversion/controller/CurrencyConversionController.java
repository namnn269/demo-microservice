package com.example.currencyconversion.controller;

import com.example.currencyconversion.dto.CurrencyConversionDto;
import com.example.currencyconversion.service.CurrencyConversionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CurrencyConversionController {

    private final CurrencyConversionService service;

    //    @Retry(name = "currencyRetry", fallbackMethod = "convertCurrencyFallback")
    @GetMapping("/currency-conversion/from/{from}/to/{to}/{amount}")
    public ResponseEntity<CurrencyConversionDto> convertCurrencyRestTemplate(@PathVariable("from") String from,
                                                                             @PathVariable("to") String to,
                                                                             @PathVariable("amount") Integer fromAmount) {
        log.info("CurrencyConversionController::Log BEFORE call rest api");
        CurrencyConversionDto dto = service.getWithRestTemplate(from, to, fromAmount);
        log.info("CurrencyConversionController::Log AFTER call rest api");
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/{amount}")
    public ResponseEntity<CurrencyConversionDto> convertCurrencyOpenFeign(@PathVariable("from") String from,
                                                                          @PathVariable("to") String to,
                                                                          @PathVariable("amount") Integer fromAmount) {
        log.info("CurrencyConversionController::Log BEFORE call feign api");
        CurrencyConversionDto dto = service.getWithOpenFeign(from, to, fromAmount);
        dto = service.getWithOpenFeign(from, to, fromAmount);
        log.info("CurrencyConversionController::Log AFTER call feign api");
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<String> convertCurrencyFallback(Exception ex) {
        log.error("ERROR::CurrencyConversionController.convertCurrencyFallback()");
        CurrencyConversionDto dto = CurrencyConversionDto.builder()
                .from("Error")
                .to("Error")
                .originalAmount(-1)
                .exchangeRate(-1)
                .build();
        return ResponseEntity.ok("ERROR lol");
    }

}
