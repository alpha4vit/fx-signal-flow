package ru.snptech.fxsignalflow.service.scenario.bot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.snptech.fxsignalflow.entity.BotClient;
import ru.snptech.fxsignalflow.model.client.ChatStatus;
import ru.snptech.fxsignalflow.model.client.ChatType;
import ru.snptech.fxsignalflow.model.client.ClientStatus;
import ru.snptech.fxsignalflow.repository.ClientRepository;

@Component
@RequiredArgsConstructor
public class BotUpdateScenario {

    private final ClientRepository clientRepository;

    @Transactional
    public void invoke(Update update) {
        var memberUpdated = update.getMyChatMember();
        var chat = memberUpdated.getChat();
        var chatType = ChatType.fromValue(chat.getType());

        if (ChatType.CHANNEL.equals(chatType)) {
            var status = ChatStatus.fromValue(memberUpdated.getNewChatMember().getStatus());

            if (status.isInvited()) {
                clientRepository.save(
                    BotClient.builder()
                        .chatId(chat.getId())
                        .title(chat.getTitle())
                        .status(ClientStatus.ACTIVE)
                        .build()
                );
            } else if (status.isKicked()) {
                clientRepository.deleteByChatId(chat.getId());
            }
        }
    }

}
