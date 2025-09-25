package ru.snptech.fxsignalflow.repository.impl;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.snptech.fxsignalflow.entity.BotClient;
import ru.snptech.fxsignalflow.repository.ClientRepository;

@Repository
public interface JdbcClientRepository extends CrudRepository<BotClient, Long>, ClientRepository {
}
