package ru.snptech.fxsignalflow.model.common;


import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.objects.Update;

@UtilityClass
public class ServiceConstantHolder {

    // TELEGRAM
    public static final MetaDataKey<String> CHAT_ID = new MetaDataKey<>("chatId", String.class);
    public static final MetaDataKey<Update> TG_UPDATE = new MetaDataKey<>("tgUpdate", Update.class);
    public static final MetaDataKey<String> SCENARIO = new MetaDataKey<>("scenario", String.class);
}
