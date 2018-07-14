package com.qless.merchantsapi.model;

import java.util.List;

public class Address {

    private List<String> addressLines;
    private String city;
    private String state;
    private String postalCode;
    private String isoCountryCode;

    public Address() {
    }

    public Address(List<String> addressLines, String city, String state, String postalCode, String isoCountryCode) {
        this.addressLines = addressLines;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.isoCountryCode = isoCountryCode;
    }

    public List<String> getAddressLines() {
        return addressLines;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getIsoCountryCode() {
        return isoCountryCode;
    }
}
