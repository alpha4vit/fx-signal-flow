package ru.snptech.fxsignalflow.model.common;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MessageConstants {



    public static final String GREETING_MESSAGE =
        """
            Добрый день, %s, добро пожаловать в бот для отправки сигналов!
                        
            Формат для отправки:
            XAUUSD SELL NOW @ 3726
                        
            SL 3730
                        
            TP1 3720
            TP2 3715
            TP3 3710
            TP4 3705
            TP5 3700
            TP6 3690
            """;

    public static final String SIGNAL_SUCCESSFULLY_SENT = """
            Сигнал успешно отправлен в каналы:
            
            %s
        """;

}
