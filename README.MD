

# Merchants API Challenge for QLess

<p>This is an example API built on top of Spring Boot + MongoDB + Spock as part of a challenge in the recruiting process for QLess.</p>

The requirement is simple, build an API that matches the documentation in [\src\main\resources\merchant-locator-swagger.json](\src\main\resources\merchant-locator-swagger.json)
using the initial data in [\src\main\resources\locations.json](\src\main\resources\locations.json)

## Requirements
* Java 8+
* Mongo DB

## Installation and Running

* Download the zip file or clone the repository
* From the folder execute
```bash
./gradlew clean build

./gradlew clean bootRun
```
## Key points to see as an example
* API implementation and validation of parameters.
* Error handling to build the response (code + message).
* Use of Criteria and MongoTemplate to build a dynamic query.
* Testing using Spock + Groovy.


## Testing

### Integration tests
I've used Spock to show how data tables work and the possibilities. For example, there's no need in using a lambda for validation, but it ilustrates how to use it.
### UAT Tests
To run the tests, install Postman and import the collection. You can run the tests one by one.
```
src\test\QLess.postman_collection
```

## Possible improvements / out of scope
* Database Initialization could be done in a better way but I decided to do it that way to save time.
* Add a Repositories layer. I've decided to use Criteria in a service as there's no need to do it for this example.
* Configure Gradle to run the tests
