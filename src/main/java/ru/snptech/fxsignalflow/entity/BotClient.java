package ru.snptech.fxsignalflow.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import ru.snptech.fxsignalflow.model.client.ClientStatus;

import java.time.Instant;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bot_client")
public class BotClient {

    @Id
    @Column(name = "chat_id")
    private Long chatId;

    private String title;

    @Enumerated(value = EnumType.STRING)
    private ClientStatus status;

    @CreationTimestamp
    private Instant createdAt;

}
