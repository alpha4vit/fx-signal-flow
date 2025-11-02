package ru.snptech.fxsignalflow.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import ru.snptech.fxsignalflow.model.translate.TranslateRequest;
import ru.snptech.fxsignalflow.model.translate.TranslateResponse;

@FeignClient(
    name = "translator-client",
    url = "${application.translator.url}",
    configuration = FeignClientConfiguration.class
)
public interface TranslatorClient {

    @PostMapping(value = "/translate", produces = MediaType.APPLICATION_JSON_VALUE)
    TranslateResponse getTranslate(TranslateRequest request);

}
