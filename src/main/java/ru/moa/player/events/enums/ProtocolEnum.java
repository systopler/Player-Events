package ru.moa.player.events.enums;

public enum ProtocolEnum {
    POP3("pop3")
    , IMAP("imap");

    private String name;

    ProtocolEnum(String name) {
        this.name = name;
    }
}
