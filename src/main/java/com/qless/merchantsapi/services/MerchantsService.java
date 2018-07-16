package com.qless.merchantsapi.services;

import com.qless.merchantsapi.model.Location;

import java.util.List;

public interface MerchantsService {


    List<Location> filterAll(String searchText, Double longitude, Double latitude, Integer searchRadius, String[] gids, Integer maximumResults, Boolean mobileClientAccess, Boolean omitMerchantInfo, Boolean omitContactInfo, Boolean omitConsumerFeatures);

    Location findByGID(Boolean mobileClientAccess, Boolean omitMerchantInfo, Boolean omitContactInfo, Boolean omitConsumerFeatures, String location_gid);

    void restoreDatabase(String fileName);
}
