package ru.snptech.fxsignalflow.exception;

import lombok.Getter;

@Getter
public abstract class BaseFxSignalFlowException extends RuntimeException {
    public BaseFxSignalFlowException(final String message) {
        super(message);
    }

}
