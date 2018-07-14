package com.qless.merchantsapi.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qless.merchantsapi.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@Service("merchantsService")
public class MerchantsServiceImpl implements MerchantsService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Location> filterAll(String searchText, Double longitude, Double latitude, Integer searchRadius, String[] gids, Integer maximumResults,
                                    Boolean mobileClientAccess, Boolean omitMerchantInfo, Boolean omitContactInfo, Boolean omitConsumerFeatures) {

        Criteria criteria = new Criteria();
        if (searchText != null) {
            criteria.orOperator(new Criteria("name").regex("^" + searchText, "i"),
                    new Criteria("description").regex("^" + searchText, "i"));
        }

        if (longitude != null && latitude != null) {
            criteria.and("contactInfo.position").withinSphere(new Circle(new Point(longitude, latitude), new Distance(searchRadius, Metrics.KILOMETERS)));
        }
        if (gids != null && gids.length > 0) {
            criteria.and("source.globalId").in(gids);
        }

        addMobileAccessCondition(mobileClientAccess, criteria);

        Query query = new Query(criteria);
        query.limit(maximumResults);

        setFilteredFields(omitMerchantInfo, omitContactInfo, omitConsumerFeatures, query);

        return mongoTemplate.find(query, Location.class, "locations");
    }


    @Override
    public Location findByGID(Boolean mobileClientAccess, Boolean omitMerchantInfo, Boolean omitContactInfo, Boolean omitConsumerFeatures, String location_gid) {
        Criteria criteria = new Criteria();

        if (location_gid == null) {
            return null;
        }
        criteria.and("source.globalId").is(location_gid);

        addMobileAccessCondition(mobileClientAccess, criteria);

        Query query = new Query(criteria);

        setFilteredFields(omitMerchantInfo, omitContactInfo, omitConsumerFeatures, query);

        return mongoTemplate.findOne(query, Location.class, "locations");
    }

    private void addMobileAccessCondition(Boolean mobileClientAccess, Criteria criteria) {
        if (mobileClientAccess != null) {
            criteria.and("consumerFeatures.hasMobileAccess").is(mobileClientAccess);
        }
    }

    private void setFilteredFields(Boolean omitMerchantInfo, Boolean omitContactInfo, Boolean omitConsumerFeature, Query query) {
        if (omitMerchantInfo) {
            query.fields().exclude("merchantInfo");
        }
        if (omitContactInfo) {
            query.fields().exclude("contactInfo");
        }
        if (omitConsumerFeature) {
            query.fields().exclude("consumerFeatures");
        }
    }

    @Override
    public void restoreDatabase(String fileName) {
        Location[] locations;
        try {
            mongoTemplate.remove(new Query(), "locations");
            locations = (new ObjectMapper()).readValue(ResourceUtils.getFile("classpath:" + fileName), Location[].class);
            Stream.of(locations).forEach(l -> {
                l.getContactInfo().setPosition();
                mongoTemplate.save(l);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
