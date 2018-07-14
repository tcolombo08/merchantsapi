package com.qless.merchantsapi.services;

import com.qless.merchantsapi.model.Location;

import java.util.List;

public interface MerchantsService {


    List<Location> filterAll(String searchText, Double longitude, Double latitude, Integer searchRadius, String[] gid, Integer maximumResults, Boolean mobileClientAccess, Boolean omitMerchantInfo, Boolean omitContactInfo, Boolean omitConsumerFeature);

    Location findByGID(Boolean mobileClientAccess, Boolean omitMerchantInfo, Boolean omitContactInfo, Boolean omitConsumerFeatures, String location_gid);

    void restoreDatabase(String fileName);
}
