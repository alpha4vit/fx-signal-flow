package ru.snptech.fxsignalflow.telegram.client;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import ru.snptech.fxsignalflow.telegram.TelegramUtils;

import java.io.File;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class TelegramClientAdapter {

    private final TelegramClient telegramClient;

    @SneakyThrows
    public Integer sendMessage(Long chatId, String text) {
        var message = SendMessage.builder()
            .chatId(chatId)
            .text(TelegramUtils.escapeMarkdownV2(text))
            .parseMode(ParseMode.MARKDOWNV2)
            .build();

        return telegramClient.execute(message).getMessageId();
    }

    @SneakyThrows
    public Integer sendMessage(Long chatId, String text, List<MessageEntity> entities) {
        var message = SendMessage.builder()
            .chatId(chatId)
            .text(text)
            .entities(entities)
            .build();

        return telegramClient.execute(message).getMessageId();
    }

    @SneakyThrows
    public Integer sendMessage(Long chatId, String text, String parseMode) {
        var message = SendMessage.builder()
            .chatId(chatId)
            .text(text)
            .parseMode(parseMode)
            .build();

        return telegramClient.execute(message).getMessageId();
    }

    @SneakyThrows
    public Integer sendMessage(Long chatId, String text, ReplyKeyboard replyKeyboard) {
        var message = SendMessage.builder()
            .chatId(chatId)
            .text(TelegramUtils.escapeMarkdownV2(text))
            .replyMarkup(replyKeyboard)
            .parseMode(ParseMode.MARKDOWNV2)
            .build();

        return telegramClient.execute(message).getMessageId();
    }

    @SneakyThrows
    public Integer sendMessage(Long chatId, String text, ReplyKeyboard replyKeyboard, String parseMode) {
        var message = SendMessage.builder()
            .chatId(chatId)
            .text(text)
            .replyMarkup(replyKeyboard)
            .parseMode(parseMode)
            .build();

        return telegramClient.execute(message).getMessageId();
    }

    @SneakyThrows
    public void sendPhoto(Long chatId, File file) {
        var inputFile = new InputFile(file);

        var message = SendPhoto.builder()
            .chatId(chatId)
            .photo(inputFile)
            .build();

        telegramClient.execute(message);
    }

    @SneakyThrows
    public void sendPhoto(Long chatId, File file, String caption) {
        var inputFile = new InputFile(file);

        var message = SendPhoto.builder()
            .chatId(chatId)
            .photo(inputFile)
            .caption(TelegramUtils.escapeMarkdownV2(caption))
            .parseMode(ParseMode.MARKDOWNV2)
            .build();

        telegramClient.execute(message);
    }

    @SneakyThrows
    public void sendPhoto(Long chatId, File file, String caption, InlineKeyboardMarkup replyKeyboard) {
        var inputFile = new InputFile(file);

        var message = SendPhoto.builder()
            .chatId(chatId)
            .photo(inputFile)
            .caption(TelegramUtils.escapeMarkdownV2(caption))
            .parseMode(ParseMode.MARKDOWNV2)
            .replyMarkup(replyKeyboard)
            .build();

        telegramClient.execute(message);
    }

    @SneakyThrows
    public void sendFile(Long chatId, File file) {
        var inputFile = new InputFile(file);

        var message = SendDocument.builder()
            .chatId(chatId)
            .document(inputFile)
            .build();

        telegramClient.execute(message);
    }

    @SneakyThrows
    public Integer updateMessage(Integer messageId, Long chatId, String text, InlineKeyboardMarkup replyKeyboard) {
        EditMessageText editMessage = EditMessageText.builder()
            .chatId(chatId.toString())
            .messageId(messageId)
            .text(TelegramUtils.escapeMarkdownV2(text))
            .parseMode(ParseMode.MARKDOWNV2)
            .replyMarkup(replyKeyboard)
            .build();

        telegramClient.execute(editMessage);

        return messageId;
    }

    @SneakyThrows
    public void releaseCallback(String callbackId) {
        telegramClient.execute(new AnswerCallbackQuery(callbackId));
    }

}
