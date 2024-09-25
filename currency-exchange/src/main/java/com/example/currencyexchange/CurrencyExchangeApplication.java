package com.example.currencyexchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CurrencyExchangeApplication {

    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println(arg);
            System.out.println("============================");
        }
        SpringApplication.run(CurrencyExchangeApplication.class, args);
    }

}
