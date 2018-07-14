package com.qless.merchantsapi.model;

public class PhoneNumber {

    private String countryCode;
    private String digits;

    public PhoneNumber() {
    }

    public PhoneNumber(String countryCode, String digits) {
        this.countryCode = countryCode;
        this.digits = digits;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getDigits() {
        return digits;
    }
}
