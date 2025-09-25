package ru.snptech.fxsignalflow.model.common;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class AdminMessageConstants {

    public static final String NEW_SURVEY_MESSAGE_TEMPLATE = """
        *Новая заявка %s*
                    
        *ID:* %s
        *ФИО:* %s
        *Номер мобильного телефона:* %s
        *Ник Telegram:* %s
        *Id Telegram:* %d
        *WhatsApp: * %s
        """;

    public static final String SURVEY_ACCEPT_MESSAGE = "Анкета успешно одобрена. Ожидание оплаты от пользователя.";

    public static final String SURVEY_DECLINED_MESSAGE = "Анкета успешно отклонена. Пользователь заблокирован до %s";

}
