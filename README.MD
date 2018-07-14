

# Merchants API Challenge for QLess

This is an example API on Spring Boot + MongoDB + Spock

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

## Testing
For some reason I couldn't make spock connect to the MongoDB instance so I had to make integration tests from Postman.
There are several tests in a postman collection in
```
src\test\QLess.postman_collection
```

To run the tests, install Postman and import the collection. You can run the tests one by one.

## Pendings
This is a timeboxed challenge and there are pendings because of the lack of time.

* Better testing inside the project. I believe there is no need of unit testing, but integration tests are needed.
* Validate that the geoposition query is accurate and working fine.

## Possible improvements
* Database Initialization could be done in a better way but I decided to do it that way to save time.
* Add a Repositories layer. I've decided to use Criteria in a service as there's no need to do it for this example.

