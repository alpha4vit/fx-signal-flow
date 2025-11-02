package ru.snptech.fxsignalflow.model.common;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MessageConstants {

    public static final String GREETING_MESSAGE =
        """
            Добрый день, %s, добро пожаловать в бот для отправки сигналов!
                        
            Формат для отправки:
            XAUUSD SELL 3726
                        
            SL 3730
                        
            TP 3720
            TP 3715
            TP 3710
            TP 3705
            TP 3700
            TP 3690
            """;

    public static final String SIGNAL_SUCCESSFULLY_SENT = """
        Сигнал успешно отправлен в каналы:
            
        %s
        """;

    public static final String SIGNAL_SENT_ERROR = """
        Ошибка отправки сигнала в канал: %s
        """;


    public static final String CLIENTS_NOT_FOUND = """
        Ошибка отправки сигнала, подписанные каналы не найдены
        """;

}
