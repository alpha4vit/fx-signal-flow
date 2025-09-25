package ru.snptech.fxsignalflow.repository;

import ru.snptech.fxsignalflow.entity.BotClient;

import java.util.List;

public interface ClientRepository {

    BotClient save(BotClient user);

    void deleteByChatId(Long chatId);

    List<BotClient> findAll();
}
