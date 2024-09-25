package com.example.currencyconversion.openfeign;

import com.example.currencyconversion.dto.CurrencyExchangeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeServiceProxy {

    @GetMapping(value = "/currency-exchange/from/{from}/to/{to}")
    ResponseEntity<CurrencyExchangeDto> getCurrencyExchangeRate(@PathVariable String from,
                                                                @PathVariable String to);

}
