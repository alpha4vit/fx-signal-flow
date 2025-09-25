
package ru.snptech.fxsignalflow.exception;

public class FxSignalFlowDomainLogicException extends BaseFxSignalFlowException {


    public FxSignalFlowDomainLogicException(final String message) {
        super(message);
    }

    public static class MESSAGE_HAS_NO_CONTENT extends FxSignalFlowDomainLogicException {
        public MESSAGE_HAS_NO_CONTENT() {
            super("Ошибка, сообщение не содержит данных, попробуйте снова!");
        }
    }

    public static class UNABLE_TO_PARSE_SIGNAL extends FxSignalFlowDomainLogicException {
        public UNABLE_TO_PARSE_SIGNAL() {
            super("""
                Невозможно преоброзовать в корректный сигнал, формат для преобразования: 
                
                XAUUSD SELL NOW @ 3726
                                
                SL 3730
                                
                TP1 3720
                TP2 3715
                TP3 3710
                TP4 3705
                TP5 3700
                TP6 3690
                """);
        }
    }

}
