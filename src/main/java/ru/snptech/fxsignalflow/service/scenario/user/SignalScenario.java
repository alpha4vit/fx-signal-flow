package ru.snptech.fxsignalflow.service.scenario.user;

import org.springframework.stereotype.Component;
import ru.snptech.fxsignalflow.repository.ClientRepository;
import ru.snptech.fxsignalflow.service.scenario.AbstractScenario;
import ru.snptech.fxsignalflow.service.util.SignalParser;
import ru.snptech.fxsignalflow.telegram.client.TelegramClientAdapter;

import java.util.Map;

import static ru.snptech.fxsignalflow.model.common.ServiceConstantHolder.TG_UPDATE;

@Component
public class SignalScenario extends AbstractScenario {

    private final ClientRepository clientRepository;

    public SignalScenario(
        TelegramClientAdapter telegramClientAdapter,
        ClientRepository clientRepository
    ) {
        super(telegramClientAdapter);
        this.clientRepository = clientRepository;
    }

    public void invoke(Map<String, Object> requestContext) {
        var tgUpdate = TG_UPDATE.getValue(requestContext);
        var message = tgUpdate.getMessage().getText();

        var parsedSignal = SignalParser.parseSignal(message);

        clientRepository.findAll().forEach((client) ->
            sendDirectMessage(
                client.getChatId(),
                parsedSignal
            )
        );
    }
}

//XAUUSD SELL NOW @ 3726
//
//SL 3730
//
//TP1 3720
//TP2 3715
//TP3 3710
//TP4 3705
//TP5 3700
//TP6 3690