
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
                
                XAUUSD SELL 3726
                                
                SL 3730
                                
                TP 3720
                TP 3715
                TP 3710
                TP 3705
                TP 3700
                TP 3690
                """);
        }
    }

    public static class CURRENCY_PAIR_NOT_FOUND extends FxSignalFlowDomainLogicException {
        public CURRENCY_PAIR_NOT_FOUND(String pair) {
            super("Валютная пара " + pair + " не найдена!");
        }
    }

}
