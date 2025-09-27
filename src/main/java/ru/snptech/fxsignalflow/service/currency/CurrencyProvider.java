package ru.snptech.fxsignalflow.service.currency;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.snptech.fxsignalflow.client.CurrencyClient;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CurrencyProvider {

    private final CurrencyClient currencyClient;

    @Getter
    private Map<String, String> currencies = Collections.emptyMap();

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.HOURS)
    private void init() {
        currencies = currencyClient.getCurrencies();
    }

}
