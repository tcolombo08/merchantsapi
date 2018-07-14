package com.qless.merchantsapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="locations")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Location {

    @Id
    public String id;
    private NetworkSource source;
    @Indexed
    private String name;
    @Indexed
    private String description;
    private Merchant merchantInfo;
    private LocationContactInfo contactInfo;
    private ConsumerFeatures consumerFeatures;

    public Location() {
    }

    public Location(String id, NetworkSource source, String name, String description, Merchant merchantInfo, LocationContactInfo contactInfo, ConsumerFeatures consumerFeatures) {
        this.id = id;
        this.source = source;
        this.name = name;
        this.description = description;
        this.merchantInfo = merchantInfo;
        this.contactInfo = contactInfo;
        this.consumerFeatures = consumerFeatures;
    }

    public String getId() {
        return id;
    }

    public NetworkSource getSource() {
        return source;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Merchant getMerchantInfo() {
        return merchantInfo;
    }

    public LocationContactInfo getContactInfo() {
        return contactInfo;
    }

    public ConsumerFeatures getConsumerFeatures() {
        return consumerFeatures;
    }
}
