package ru.snptech.fxsignalflow.repository.impl;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.snptech.fxsignalflow.entity.TelegramUser;
import ru.snptech.fxsignalflow.repository.UserRepository;

@Repository
public interface JdbcUserRepository extends CrudRepository<TelegramUser, Long>, UserRepository {

    @Override
    TelegramUser findByChatId(Long chatId);

}
