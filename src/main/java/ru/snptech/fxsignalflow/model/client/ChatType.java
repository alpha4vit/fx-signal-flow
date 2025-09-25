package ru.snptech.fxsignalflow.model.client;

public enum ChatType {
    CHANNEL("channel"),
    SUPERGROUP("supergroup"),
    GROUP("group"),
    PRIVATE("private");

    private String value;

    ChatType(final String value) {
        this.value = value;
    }

    public static ChatType fromValue(String value) {
        for (ChatType type : ChatType.values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown ChatType value: " + value);
    }
}
