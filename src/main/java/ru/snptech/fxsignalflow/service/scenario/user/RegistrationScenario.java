package ru.snptech.fxsignalflow.service.scenario.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.snptech.fxsignalflow.entity.TelegramUser;
import ru.snptech.fxsignalflow.model.scenario.ScenarioType;
import ru.snptech.fxsignalflow.repository.UserRepository;
import ru.snptech.fxsignalflow.service.user.UserContextService;
import ru.snptech.fxsignalflow.telegram.TelegramUtils;

import java.util.Map;
import java.util.stream.Collectors;

import static ru.snptech.fxsignalflow.model.common.ServiceConstantHolder.*;

@Component
@RequiredArgsConstructor
public class RegistrationScenario {
    private final UserRepository userRepository;
    private final UserContextService userContextService;

    public TelegramUser invoke(Map<String, Object> requestContext) {
        var tgUpdate = TG_UPDATE.getValue(requestContext);
        var chatId = TelegramUtils.extractChatIdFromUpdate(tgUpdate);
        var user = userRepository.findByChatId(chatId);

        if (user != null) {
            var context = userContextService.getUserContext(user);

            Map<String, Object> contextWithoutUpdates = context.entrySet().stream()
                .filter(entry -> !entry.getKey().equals(TG_UPDATE.toString()))
                .collect(Collectors.toMap(
                    Map.Entry::getKey,
                    Map.Entry::getValue
                ));

            requestContext.putAll(contextWithoutUpdates);

            return user;
        }

        var tgUser = TelegramUtils.extractUserFromUpdate(tgUpdate);
        user = new TelegramUser();
        user.setChatId(chatId);
        user.setTelegramFirstName(tgUser.getFirstName());
        user.setTelegramLastName(tgUser.getLastName());
        user.setTelegramUsername(tgUser.getUserName());
        user = userRepository.save(user);

        CHAT_ID.setValue(requestContext, user.getChatId().toString());
        SCENARIO.setValue(requestContext, ScenarioType.GREETING_MESSAGE.name());

        userContextService.updateUserContext(user, requestContext);

        return user;
    }
}
