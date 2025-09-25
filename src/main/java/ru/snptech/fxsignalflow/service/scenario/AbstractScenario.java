package ru.snptech.fxsignalflow.service.scenario;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import ru.snptech.fxsignalflow.telegram.client.TelegramClientAdapter;

import java.util.Map;

import static ru.snptech.fxsignalflow.model.common.ServiceConstantHolder.CHAT_ID;

@RequiredArgsConstructor
abstract public class AbstractScenario {

    protected final TelegramClientAdapter telegramClientAdapter;

    @SneakyThrows
    protected void sendMessage(Map<String, Object> requestContext, String message) {
        telegramClientAdapter.sendMessage(
            CHAT_ID.getValue(requestContext, Long.class),
            message
        );
    }

    @SneakyThrows
    protected void sendDirectMessage(Long chatId, String message) {
        telegramClientAdapter.sendMessage(
            chatId,
            message
        );
    }


}
