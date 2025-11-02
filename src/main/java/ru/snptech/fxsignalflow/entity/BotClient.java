package ru.snptech.fxsignalflow.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import ru.snptech.fxsignalflow.model.client.ClientStatus;
import ru.snptech.fxsignalflow.model.translate.Language;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

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

    @Builder.Default
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "client_consumers",
        joinColumns = @JoinColumn(name = "producer_id"),
        inverseJoinColumns = @JoinColumn(name = "consumer_id")
    )
    private List<BotClient> consumers = Collections.emptyList();

    @Builder.Default
    @Enumerated(value = EnumType.STRING)
    private Language language = Language.RU;
}
