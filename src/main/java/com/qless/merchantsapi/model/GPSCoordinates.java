package com.qless.merchantsapi.model;

public class GPSCoordinates {
    private Double latitude;
    private Double longitude;

    public GPSCoordinates() {
    }

    public GPSCoordinates(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
