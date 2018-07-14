package com.qless.merchantsapi.model;

import org.springframework.data.annotation.Transient;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;

import static org.springframework.data.mongodb.core.index.GeoSpatialIndexType.GEO_2D;

public class LocationContactInfo {

    private String timeZone;
    private Address address;
    private PhoneNumber phone;
    private PhoneNumber fax;
    @Transient
    private GPSCoordinates gps;

    @GeoSpatialIndexed(type = GEO_2D)
    Point position;

    public LocationContactInfo() {
    }

    public LocationContactInfo(String timeZone, Address address, PhoneNumber phone, PhoneNumber fax, GPSCoordinates gps) {
        this.timeZone = timeZone;
        this.address = address;
        this.phone = phone;
        this.fax = fax;
        this.position = new Point(gps.getLongitude(), gps.getLatitude());
    }

    public String getTimeZone() {
        return timeZone;
    }

    public Address getAddress() {
        return address;
    }

    public PhoneNumber getPhone() {
        return phone;
    }

    public PhoneNumber getFax() {
        return fax;
    }

    public GPSCoordinates getGps() {
        return gps;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition() {
        this.position = new Point(gps.getLongitude(), gps.getLatitude());
    }
}
