package com.qless.merchantsapi.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.qless.merchantsapi.model.Location
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Query
import org.springframework.util.ResourceUtils
import spock.lang.Specification
import spock.lang.Unroll

import java.util.stream.Stream

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest( webEnvironment = RANDOM_PORT)
class MerchantsApiIntegrationSpec extends Specification {

    @Autowired
    private MerchantsService merchantsService

    @Autowired
    MongoTemplate mongoTemplate

    void setup() {
        Location[] locations
        try {
            mongoTemplate.remove(new Query(), "locations")
            locations = (new ObjectMapper()).readValue(ResourceUtils.getFile("classpath: locations-test.json"), Location[].class)
            Stream.of(locations).forEach( {
                it.getContactInfo().setPosition()
                mongoTemplate.save(it)
            })
        } catch (IOException e) {
            e.printStackTrace()
        }
    }

    @Unroll
    def "search merchants filtered by text" () {
        when: "Text is in name"

        def location = merchantsService.findByGID(null, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, '77BD8E97FE581AD553D656BAF60BB7B199C275D1')

        then: "Location is not null"
            location != null
    }
}