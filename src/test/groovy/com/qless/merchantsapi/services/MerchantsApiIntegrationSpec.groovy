package com.qless.merchantsapi.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.qless.merchantsapi.model.Location
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Query
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Unroll

import java.util.stream.Stream

import static java.lang.Boolean.FALSE
import static java.lang.Boolean.TRUE
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@ContextConfiguration
@SpringBootTest(webEnvironment = RANDOM_PORT)
class MerchantsApiIntegrationSpec extends Specification {

    @Autowired
    private MerchantsService merchantsService

    @Autowired
    MongoTemplate mongoTemplate

    void setup() {
        Location[] locations
        try {
            mongoTemplate.remove(new Query(), "locations")

            locations = (new ObjectMapper()).readValue(new File("src/test/resources/locations-test.json"), Location[].class)
            Stream.of(locations).forEach({
                it.getContactInfo().setPosition()
                mongoTemplate.save(it)
            })
        } catch (IOException e) {
            e.printStackTrace()
        }
    }

    @Unroll
    def "filter merchants by #description"() {

        when: "Filtering locations"

        def locations = merchantsService.filterAll(text, lon, lat, rad, gids, maxRes, mobAccess, omitMerch, omitCont, omitCons)

        then:
        "Locations count should be #count"
        locations.size() == count

        and:
        validateOmittedFields(locations, omitMerch, omitCont, omitCons)

        where: "Testing parameters"
        description         | text           | lon                | lat       | rad   | gids                                                     | maxRes | mobAccess | omitMerch | omitCont | omitCons || count
        "No filters"        | null           | null               | null      | 50000 | null                                                     | 20     | null      | FALSE     | FALSE    | FALSE    || 3
        "Not present text"  | "Show nothing" | null               | null      | 50000 | null                                                     | 20     | null      | TRUE      | FALSE    | FALSE    || 0
        "Text"              | "Johnson"      | null               | null      | 50000 | null                                                     | 20     | null      | TRUE      | TRUE     | FALSE    || 2
        "Text + Gid"        | "Johnson"      | null               | null      | 50000 | ["EF7BE5A5EE23A51D339A43CDC1D62982EB61CC3F"] as String[] | 20     | null      | TRUE      | TRUE     | TRUE     || 1
        "Text + Gid + !Mob" | "Johnson"      | null               | null      | 50000 | ["EF7BE5A5EE23A51D339A43CDC1D62982EB61CC3F"] as String[] | 20     | FALSE     | FALSE     | FALSE    | TRUE     || 0
        "Text + Gid + Mob"  | "Johnson"      | null               | null      | 50000 | ["EF7BE5A5EE23A51D339A43CDC1D62982EB61CC3F"] as String[] | 20     | TRUE      | FALSE     | FALSE    | TRUE     || 1
        "GPS 1 in rad"      | null           | -94.67940340000001 | 39.073135 | 7     | null                                                     | 20     | null      | FALSE     | FALSE    | FALSE    || 1
        "GPS 2 in rad"      | null           | -94.67940340000001 | 39.073135 | 23    | null                                                     | 20     | null      | FALSE     | FALSE    | FALSE    || 2
    }


    @Unroll
    def "Find by Global Id with: #description"() {

        when: "Call findByGID"
        def location = merchantsService.findByGID(mobAccess,omitMerch, omitCont, omitCons, gid )

        then:
        isValid(location)

        and:
            location == null || !(omitMerch && (location.merchantInfo != null)) && !(omitCont && (location.contactInfo != null)) && !(omitCons && (location.consumerFeatures != null))

        where: "Testing parameters"
        description                                   | gid                                         | mobAccess | omitMerch | omitCont | omitCons || isValid
        "Existing Gid and Right mobile access"        | "EF7BE5A5EE23A51D339A43CDC1D62982EB61CC3F"  | TRUE      | TRUE      | TRUE     | TRUE     || {it != null}
        "Existing Gid and Right wrong mobile access"  | "EF7BE5A5EE23A51D339A43CDC1D62982EB61CC3F"  | FALSE     | FALSE     | FALSE    | TRUE     || {it == null}
        "Existing Gid and mobile access not filtered" | "EF7BE5A5EE23A51D339A43CDC1D62982EB61CC3F"  | null      | FALSE     | FALSE    | TRUE     || {it != null}

    }

    private boolean validateOmittedFields(List<Location> locations, Boolean omitMerch, Boolean omitCont, Boolean omitCons) {
        !(omitMerch && !merchantInfosAreNull(locations)) && !(omitCont && !contactInfosAreNull(locations)) && !(omitCons && !consumerFeaturesAreNull(locations))
    }

    private boolean merchantInfosAreNull(List<Location> locations) {
        locations.findAll { it.merchantInfo != null }.size() == 0
    }

    private boolean consumerFeaturesAreNull(List<Location> locations) {
        locations.findAll { it.consumerFeatures != null }.size() == 0
    }

    private boolean contactInfosAreNull(List<Location> locations) {
        locations.findAll { it.contactInfo != null }.size() == 0
    }
}