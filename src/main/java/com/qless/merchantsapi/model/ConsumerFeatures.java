package com.qless.merchantsapi.model;

import java.util.List;

public class ConsumerFeatures {

    private Boolean hasAppointments;
    private Boolean hasCustomScreens;
    private Boolean hasMobileAccess;
    private Boolean hasSMSSummoning;
    private Boolean hasTransactionTypes;
    private Boolean hasVoiceQueuing;
    private Integer maxSimultaneousQueues;
    private Boolean shouldDisplayTransactionTypesBeforeQueues;
    private List<String> supportedLocales;
    private Boolean supportsPartySize;

    public ConsumerFeatures() {
    }

    public ConsumerFeatures(Boolean hasAppointments, Boolean hasCustomScreens, Boolean hasMobileAccess, Boolean hasSMSSummoning,
                            Boolean hasTransactionTypes, Boolean hasVoiceQueuing, Integer maxSimultaneousQueues,
                            Boolean shouldDisplayTransactionTypesBeforeQueues, List<String> supportedLocales, Boolean supportsPartySize) {
        this.hasAppointments = hasAppointments;
        this.hasCustomScreens = hasCustomScreens;
        this.hasMobileAccess = hasMobileAccess;
        this.hasSMSSummoning = hasSMSSummoning;
        this.hasTransactionTypes = hasTransactionTypes;
        this.hasVoiceQueuing = hasVoiceQueuing;
        this.maxSimultaneousQueues = maxSimultaneousQueues;
        this.shouldDisplayTransactionTypesBeforeQueues = shouldDisplayTransactionTypesBeforeQueues;
        this.supportedLocales = supportedLocales;
        this.supportsPartySize = supportsPartySize;
    }

    public Boolean getHasAppointments() {
        return hasAppointments;
    }

    public Boolean getHasCustomScreens() {
        return hasCustomScreens;
    }

    public Boolean getHasMobileAccess() {
        return hasMobileAccess;
    }

    public Boolean getHasSMSSummoning() {
        return hasSMSSummoning;
    }

    public Boolean getHasTransactionTypes() {
        return hasTransactionTypes;
    }

    public Boolean getHasVoiceQueuing() {
        return hasVoiceQueuing;
    }

    public Integer getMaxSimultaneousQueues() {
        return maxSimultaneousQueues;
    }

    public Boolean getShouldDisplayTransactionTypesBeforeQueues() {
        return shouldDisplayTransactionTypesBeforeQueues;
    }

    public List<String> getSupportedLocales() {
        return supportedLocales;
    }

    public Boolean getSupportsPartySize() {
        return supportsPartySize;
    }
}
