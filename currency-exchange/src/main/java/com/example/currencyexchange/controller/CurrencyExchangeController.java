package com.example.currencyexchange.controller;

import com.example.currencyexchange.dto.CurrencyExchangeDto;
import com.example.currencyexchange.service.CurrencyExchangeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyExchangeController.class);

    private final CurrencyExchangeService currencyExchangeService;

    public CurrencyExchangeController(CurrencyExchangeService currencyExchangeService) {
        this.currencyExchangeService = currencyExchangeService;
    }

    @GetMapping("/from/{from}/to/{to}")
    public ResponseEntity<CurrencyExchangeDto> getExchange(@PathVariable("from") String from,
                                                           @PathVariable("to") String to) {
        LOGGER.info("get currency-exchange value from {} to {}", from, to);
        return ResponseEntity.ok(currencyExchangeService.getExchangeRate(from, to));
    }

}
