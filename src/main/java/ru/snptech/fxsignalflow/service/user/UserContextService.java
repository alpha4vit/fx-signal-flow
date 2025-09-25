package ru.snptech.fxsignalflow.service.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ru.snptech.fxsignalflow.entity.TelegramUser;
import ru.snptech.fxsignalflow.model.common.MetaDataKey;
import ru.snptech.fxsignalflow.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserContextService {
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    @SuppressWarnings("unchecked")
    public Map<String, Object> getUserContext(TelegramUser telegramUser) {
        return Optional
            .ofNullable(telegramUser.getContext())
            .map(stringContext -> {
                try {
                    return objectMapper.readValue(stringContext, Map.class);
                } catch (JsonProcessingException e) {
                    return new HashMap<String, Object>();
                }
            })
            .orElse(new HashMap<String, Object>());
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getUserContext(Long chatId) {
        var user = userRepository.findByChatId(chatId);

        return Optional
            .ofNullable(user.getContext())
            .map(stringContext -> {
                try {
                    return objectMapper.readValue(stringContext, Map.class);
                } catch (JsonProcessingException e) {
                    return new HashMap<String, Object>();
                }
            })
            .orElse(new HashMap<String, Object>());
    }

    @SneakyThrows
    public void updateUserContext(TelegramUser telegramUser, Map<String, Object> userContext) {
        telegramUser.setContext(objectMapper.writeValueAsString(userContext));

        userRepository.save(telegramUser);
    }

    public <T> void updateUserContext(TelegramUser telegramUser, MetaDataKey<T> metaDataKey, T value) {
        var context = getUserContext(telegramUser);
        metaDataKey.setValue(context, value);
        updateUserContext(telegramUser, context);
    }

    public <T> T getUserContextParamValue(TelegramUser telegramUser, MetaDataKey<T> metaDataKey) {
        return metaDataKey.getValue(getUserContext(telegramUser));
    }
}
