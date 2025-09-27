package ru.snptech.fxsignalflow.service.scenario.user;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import ru.snptech.fxsignalflow.exception.FxSignalFlowDomainLogicException;
import ru.snptech.fxsignalflow.model.common.MessageConstants;
import ru.snptech.fxsignalflow.model.scenario.ScenarioType;
import ru.snptech.fxsignalflow.repository.UserRepository;
import ru.snptech.fxsignalflow.service.scenario.AbstractScenario;
import ru.snptech.fxsignalflow.service.user.UserContextService;
import ru.snptech.fxsignalflow.telegram.client.TelegramClientAdapter;

import java.util.Map;

import static ru.snptech.fxsignalflow.model.common.ServiceConstantHolder.*;


@Component
public class UserUpdateScenario extends AbstractScenario {

    private final SignalScenario signalScenario;
    private final UserContextService userContextService;
    private final UserRepository userRepository;

    public UserUpdateScenario(
        UserContextService userContextService,
        UserRepository userRepository,
        TelegramClientAdapter telegramClientAdapter,
        SignalScenario signalScenario
    ) {
        super(telegramClientAdapter);

        this.userContextService = userContextService;
        this.userRepository = userRepository;
        this.signalScenario = signalScenario;
    }

    @SneakyThrows
    public void invoke(Map<String, Object> requestContext) {
        if (!TG_UPDATE.getValue(requestContext).hasMessage()) {
            throw new FxSignalFlowDomainLogicException.MESSAGE_HAS_NO_CONTENT();
        }

        var chatId = CHAT_ID.getValue(requestContext, Long.class);

        resetToMainMenuContextIfNeeded(chatId, requestContext);

        var currentScenario = SCENARIO.getValue(requestContext, ScenarioType.class);

        switch (currentScenario) {
            case WAITING_SIGNALS -> {
                signalScenario.invoke(requestContext);
            }

            case GREETING_MESSAGE -> {
                var user = userRepository.findByChatId(chatId);

                sendMessage(requestContext, MessageConstants.GREETING_MESSAGE.formatted(user.getTelegramFirstName()));

                SCENARIO.setValue(requestContext, ScenarioType.WAITING_SIGNALS.name());
                userContextService.updateUserContext(user, requestContext);
            }

            default -> {
                // nothing for now
            }
        }
    }

    private void resetToMainMenuContextIfNeeded(Long chatId, Map<String, Object> requestContext) {
        if (
            TG_UPDATE.getValue(requestContext).getMessage().hasText()
                && ("/start".equals(TG_UPDATE.getValue(requestContext).getMessage().getText())
            )
        ) {
            var user = userRepository.findByChatId(chatId);

            SCENARIO.setValue(requestContext, ScenarioType.GREETING_MESSAGE.name());

            userContextService.updateUserContext(user, requestContext);
        }
    }
}
