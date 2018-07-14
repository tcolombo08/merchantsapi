package com.qless.merchantsapi.model;

public class NetworkSource {

    private NetworkSourceType type;
    private String globalId;
    private String hostName;
    private String hostId;
    private String indirectId;

    public NetworkSource() {
    }

    public NetworkSourceType getType() {
        return type;
    }

    public String getGlobalId() {
        return globalId;
    }

    public String getHostName() {
        return hostName;
    }

    public String getHostId() {
        return hostId;
    }

    public String getIndirectId() {
        return indirectId;
    }
}
