package com.tkzc00.kongapicommon.model.enums;

public enum InterfaceInfoStatus {
    OFFLINE("下线", 0),
    ONLINE("上线", 1);

    private final String text;
    private final int code;

    InterfaceInfoStatus(String text, int code) {
        this.text = text;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getText() {
        return text;
    }
}
