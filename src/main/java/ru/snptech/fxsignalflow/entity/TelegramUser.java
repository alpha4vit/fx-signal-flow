package ru.snptech.fxsignalflow.entity;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import java.time.Instant;

@Data
@Entity
@Table(name = "telegram_user")
public class TelegramUser {

    @Id
    @Column(name = "chat_id")
    private Long chatId;

    private String telegramUsername;

    private String telegramFirstName;

    private String telegramLastName;

    @CreationTimestamp
    private Instant createdAt;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private String context;
}
