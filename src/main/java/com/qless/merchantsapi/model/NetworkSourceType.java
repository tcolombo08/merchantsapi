package com.qless.merchantsapi.model;

public enum NetworkSourceType {
    location("location"), merchant("merchant");
    private String value;

    NetworkSourceType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
