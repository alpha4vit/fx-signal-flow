package ru.snptech.fxsignalflow.service.scenario.bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.snptech.fxsignalflow.client.TranslatorClient;
import ru.snptech.fxsignalflow.entity.BotClient;
import ru.snptech.fxsignalflow.model.translate.TranslateRequest;
import ru.snptech.fxsignalflow.repository.ClientRepository;
import ru.snptech.fxsignalflow.service.scenario.AbstractScenario;
import ru.snptech.fxsignalflow.telegram.client.TelegramClientAdapter;

import java.util.List;

@Component
public class ClientProducerScenario extends AbstractScenario {

    private final ClientRepository clientRepository;
    private final TranslatorClient translatorClient;

    public void invoke(Update update) {
        if (!update.hasChannelPost()) return;

        var post = update.getChannelPost();


        clientRepository.findById(post.getChatId())
            .ifPresent(sender -> {
                var consumers = sender.getConsumers();
                if (consumers != null && !consumers.isEmpty()) {
                    forwardToConsumers(sender, consumers, post.getText());
                }
            });
    }

    private void forwardToConsumers(BotClient sender, List<BotClient> consumers, String message) {
        consumers.forEach(consumer -> {
            var text = getTranslatedMessage(sender, consumer, message);

            sendDirectMessage(consumer.getChatId(), text);
        });
    }

    private String getTranslatedMessage(BotClient sender, BotClient consumer, String text) {
        if (sender.getLanguage().equals(consumer.getLanguage())) return text;

        var translateRequest = TranslateRequest.build(text, sender.getChatId().toString());
        return translatorClient.getTranslate(translateRequest).translatedText();
    }

    public ClientProducerScenario(
        final TelegramClientAdapter telegramClientAdapter,
        final ClientRepository clientRepository,
        final TranslatorClient translatorClient

    ) {
        super(telegramClientAdapter);
        this.clientRepository = clientRepository;
        this.translatorClient = translatorClient;
    }
}
