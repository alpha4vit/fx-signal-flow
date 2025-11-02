package ru.snptech.fxsignalflow.model.translate;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TranslateRequest(
    @JsonProperty(value = "q")
    String question,

    String source,

    String target,

    @JsonProperty(value = "api_key")
    String apiKey
) {

    public static TranslateRequest build(
        String question,
        String target
    ) {

        return new TranslateRequest(
            question,
            "auto",
            target,
            ""
        );
    }

}
