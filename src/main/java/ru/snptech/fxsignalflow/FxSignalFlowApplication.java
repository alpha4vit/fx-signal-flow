package ru.snptech.fxsignalflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.snptech.fxsignalflow.client.CurrencyClient;
import ru.snptech.fxsignalflow.client.TranslatorClient;

@EnableAsync
@EnableScheduling
@EnableFeignClients(
    clients = {
        CurrencyClient.class,
        TranslatorClient.class
    }
)
@SpringBootApplication
public class FxSignalFlowApplication {

    public static void main(String[] args) {
        SpringApplication.run(FxSignalFlowApplication.class, args);
    }

}
