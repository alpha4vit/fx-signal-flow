package ru.snptech.fxsignalflow.repository;

import ru.snptech.fxsignalflow.entity.BotClient;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {

    BotClient save(BotClient user);

    void deleteByChatId(Long chatId);

    List<BotClient> findAll();

    Optional<BotClient> findById(Long chatId);
}
