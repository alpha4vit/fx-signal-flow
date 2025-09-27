package ru.snptech.fxsignalflow.service.scenario.user;

import org.springframework.stereotype.Component;
import ru.snptech.fxsignalflow.entity.BotClient;
import ru.snptech.fxsignalflow.model.common.MessageConstants;
import ru.snptech.fxsignalflow.repository.ClientRepository;
import ru.snptech.fxsignalflow.service.scenario.AbstractScenario;
import ru.snptech.fxsignalflow.service.signal.SignalParser;
import ru.snptech.fxsignalflow.telegram.client.TelegramClientAdapter;

import java.util.Map;
import java.util.stream.Collectors;

import static ru.snptech.fxsignalflow.model.common.ServiceConstantHolder.TG_UPDATE;

@Component
public class SignalScenario extends AbstractScenario {

    private final SignalParser signalParser;
    private final ClientRepository clientRepository;

    public SignalScenario(
        TelegramClientAdapter telegramClientAdapter,
        SignalParser signalParser,
        ClientRepository clientRepository
    ) {
        super(telegramClientAdapter);
        this.signalParser = signalParser;
        this.clientRepository = clientRepository;
    }

    public void invoke(Map<String, Object> requestContext) {
        var tgUpdate = TG_UPDATE.getValue(requestContext);
        var message = tgUpdate.getMessage().getText();

        var parsedSignal = signalParser.parse(message);

        var clients = clientRepository.findAll();

        clients.forEach((client) ->
            sendDirectMessage(
                client.getChatId(),
                parsedSignal.toMessage()
            )
        );

        String names = clients.stream()
            .map(BotClient::getTitle)
            .collect(Collectors.joining("\n"));

        sendMessage(requestContext, MessageConstants.SIGNAL_SUCCESSFULLY_SENT.formatted(names));
    }
}
