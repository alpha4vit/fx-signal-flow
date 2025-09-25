package ru.snptech.fxsignalflow.model.client;

import org.checkerframework.checker.units.qual.C;

public enum ChatStatus {
    MEMBER("member"),
    ADMIN("administrator"),
    KICKED("kicked"),
    LEFT("left");

    private String value;

    ChatStatus(final String value) {
        this.value = value;
    }

    public static ChatStatus fromValue(String value) {
        for (ChatStatus type : ChatStatus.values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown ChatType value: " + value);
    }

    public Boolean isKicked() {
        return this == ChatStatus.KICKED || this == ChatStatus.LEFT;
    }

    public Boolean isInvited() {
        return this == ChatStatus.ADMIN || this == ChatStatus.MEMBER;
    }
}
