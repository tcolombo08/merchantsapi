package com.qless.merchantsapi.controllers;

import com.qless.merchantsapi.exceptions.EntityNotFoundException;
import com.qless.merchantsapi.model.Location;
import com.qless.merchantsapi.services.MerchantsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping(value = "/merchant/location")
@Validated
public class MerchantsController {

    private final MerchantsService merchantsService;

    public MerchantsController(MerchantsService merchantsService) {
        this.merchantsService = merchantsService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Location>> findLocations(@RequestParam(required = false) String searchText,
                                                                @RequestParam(required = false) Double longitude,
                                                                @RequestParam(required = false) Double latitude,
                                                                @RequestParam(required = false, defaultValue = "50000") @Positive  Integer searchRadius,
                                                                @RequestParam(required = false) String[] gids,
                                                                @RequestParam(required = false, defaultValue = "10") @Positive Integer maximumResults,
                                                                @RequestParam(required = false) Boolean mobileClientAccess,
                                                                @RequestParam(required = false, defaultValue = "false") Boolean omitMerchantInfo,
                                                                @RequestParam(required = false, defaultValue = "false") Boolean omitContactInfo,
                                                                @RequestParam(required = false, defaultValue = "false") Boolean omitConsumerFeatures) {

        List<Location> results = merchantsService.filterAll(searchText, longitude, latitude, searchRadius, gids, maximumResults,
                mobileClientAccess, omitMerchantInfo, omitContactInfo, omitConsumerFeatures);
        return ResponseEntity.ok(results);

    }

    @GetMapping(path = "/{location_gid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Location> findLocationByGID(@RequestParam(required = false) Boolean mobileClientAccess,
                                                      @RequestParam(required = false, defaultValue = "false") boolean omitMerchantInfo,
                                                      @RequestParam(required = false, defaultValue = "false") boolean omitContactInfo,
                                                      @RequestParam(required = false, defaultValue = "false") boolean omitConsumerFeatures,
                                                      @PathVariable String location_gid) {

        Location location = merchantsService.findByGID(mobileClientAccess, omitMerchantInfo, omitContactInfo, omitConsumerFeatures, location_gid);

        if (location == null) {
            throw  new EntityNotFoundException("No entities found");
        }

        return ResponseEntity.ok(location);
    }

}
