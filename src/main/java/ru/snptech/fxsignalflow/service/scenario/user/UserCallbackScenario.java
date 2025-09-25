package ru.snptech.fxsignalflow.service.scenario.user;

import lombok.SneakyThrows;

import java.util.Map;

import static ru.snptech.fxsignalflow.model.common.ServiceConstantHolder.CHAT_ID;
import static ru.snptech.fxsignalflow.model.common.ServiceConstantHolder.TG_UPDATE;

public class UserCallbackScenario {

    @SneakyThrows
    public void invoke(Map<String, Object> requestContext) {
        var chatId = CHAT_ID.getValue(requestContext, Long.class);

        var callback = TG_UPDATE.getValue(requestContext).getCallbackQuery();
    }

}
