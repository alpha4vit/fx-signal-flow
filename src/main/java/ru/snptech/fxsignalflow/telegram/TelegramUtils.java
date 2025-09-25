package ru.snptech.fxsignalflow.telegram;

import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Optional;

@UtilityClass
public class TelegramUtils {
    public static User extractUserFromUpdate(Update update) {
        if (update.hasMessage()) {
            return update.getMessage().getFrom();
        } else if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getFrom();
        } else {
            throw new UnsupportedOperationException("Невозможно определить пользователя из обновления!");
        }
    }

    public static Long extractChatIdFromUpdate(Update update) {
        if (update.hasMessage()) {
            return update.getMessage().getChat().getId();
        } else if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getFrom().getId();
        } else {
            throw new UnsupportedOperationException("Невозможно определить пользователя из обновления!");
        }
    }


    public static String escapeMarkdownV2(String str) {
        return Optional.ofNullable(str).orElse("")
                .replace("!", "\\!")
                .replace("_", "\\_")
//                .replace("*", "\\*")
                .replace("~", "\\~")
                .replace("|", "\\|")
                .replace("[", "\\[")
                .replace("]", "\\]")
                .replace("(", "\\(")
                .replace(")", "\\)")
                .replace("`", "\\`")
                .replace(".", "\\.")
                .replace("+", "\\+")
                .replace("#", "\\#")
                .replace("=", "\\=")
                .replace("}", "\\}")
                .replace("{", "\\{")
                .replace("-", "\\-")
                .replace(">", "\\>")
                .replace("&", "\\&");
    }
}
