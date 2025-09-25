package ru.snptech.fxsignalflow.model.signal;

import org.telegram.telegrambots.meta.api.objects.MessageEntity;

import java.util.List;

public record ParsedSignal(String text, List<MessageEntity> entities) {
}
