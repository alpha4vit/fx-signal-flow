package ru.snptech.fxsignalflow.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@FeignClient(
    name = "currency-client",
    url = "https://cdn.jsdelivr.net/npm/@fawazahmed0/currency-api@latest/v1",
    configuration = FeignClientConfiguration.class
)
public interface CurrencyClient {

    @GetMapping("/currencies.json")
    Map<String, String> getCurrencies();

}
