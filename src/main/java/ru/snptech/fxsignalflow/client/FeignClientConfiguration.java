package ru.snptech.fxsignalflow.client;

import feign.Logger;
import org.springframework.context.annotation.Bean;

public class FeignClientConfiguration {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

}
