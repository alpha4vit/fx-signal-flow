package ru.snptech.fxsignalflow.repository;

import ru.snptech.fxsignalflow.entity.TelegramUser;

public interface UserRepository {

    TelegramUser save(TelegramUser user);

    TelegramUser findByChatId(Long chatId);
}
