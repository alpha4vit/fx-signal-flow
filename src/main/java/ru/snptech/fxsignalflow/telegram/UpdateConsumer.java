package ru.snptech.fxsignalflow.telegram;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.snptech.fxsignalflow.service.scenario.bot.BotUpdateScenario;
import ru.snptech.fxsignalflow.service.scenario.bot.ClientProducerScenario;
import ru.snptech.fxsignalflow.service.scenario.user.RegistrationScenario;
import ru.snptech.fxsignalflow.service.scenario.user.UserUpdateScenario;
import ru.snptech.fxsignalflow.telegram.client.TelegramClientAdapter;

import java.util.HashMap;
import java.util.Map;

import static ru.snptech.fxsignalflow.model.common.ServiceConstantHolder.TG_UPDATE;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateConsumer implements LongPollingSingleThreadUpdateConsumer {

    private final BotUpdateScenario botUpdateScenario;
    private final ClientProducerScenario clientProducerScenario;
    private final UserUpdateScenario userUpdateScenario;
    private final RegistrationScenario registrationScenario;
    private final TelegramClientAdapter telegramClientAdapter;

    @Override
    public void consume(Update update) {
        botUpdateScenario.invoke(update);
        clientProducerScenario.invoke(update);

        var chatId = TelegramUtils.extractChatIdFromUpdate(update);

        try {
            Map<String, Object> requestContext = new HashMap<>();
            TG_UPDATE.setValue(requestContext, update);

            registrationScenario.invoke(requestContext);

            userUpdateScenario.invoke(requestContext);
        } catch (Throwable t) {
            telegramClientAdapter.sendMessage(chatId, t.getMessage());

            throw t;
        }

    }
}
