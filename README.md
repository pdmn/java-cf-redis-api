# Java Testing API for Redis and CloudFoundry

A very simple [Spring Boot](http://projects.spring.io/spring-boot/) application which provides 
a simple RESTful API for interacting with [Redis](http://redis.io). It is designed to run 
either in [CloudFoundry](https://www.cloudfoundry.org/).

The API supports an endpoint to perform get, set, and delete actions on Redis key/value pairs. 

The application utilizes the [Spring Cloud Connectors](http://cloud.spring.io/spring-cloud-connectors/) 
libraries to auto-configure a Redis connection using the [Jedis](https://github.com/xetorthio/jedis) client library. 

### Getting Started

#### Building

Build configurations for both [Maven](https://maven.apache.org/) and [Gradle](https://gradle.org/) are included with 
this repository. You to install either tool installed along with 
[JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html) to build the project. 


To build the project with Maven:

    mvn package
    
To build the project with Gradle:

    gradle build 
    
Maven will create the jar file at `./target/cf-redis-api-${version}.jar`

Gradle will create the jar file at `./build/libs/cf-redis-api-${version}.jar`

#### Deploying to CloudFoundry

To deploy this app into CloudFoundry:

    cf push -p path/to/cf-redis-api-${version}.jar
    
The application will only successfully boot once a single Redis service is bound to it. If deployed to 
CloudFoundry with zero, or more than one Redis applications bound the app will fail to auto-configure the Redis 
connection and crash. 

#### Usage

The API exposes the following endpoints. 

#### GET /api/key/:key

Get the value of a key. Returns `404` if the key does not exist. 

    $ curl -H "Content-Type: application/json" -X GET https://java-cf-redis-api/api/key/test
    {
      "value": "hello world"
    }

#### PUT /api/key/:key

Set the value of a key in Redis. The value of the key needs to be provided in the 'value'
field in the of the request data. Will return `"result": true` if the update was successful.
    
    $ curl -H "Content-Type: application/json" -X PUT -d '{"value": "Hooray for kittens."}' https://java-cf-redis-api/api/key/test
    {
      "result": true
    }
    
#### POST /api/key/:key

Same as `PUT`

#### DELETE /api/key/:key

Deletes the key in Redis. Returns `1` if the key was found and deleted, and `0` if the key did not exist.

    $ curl -H "Content-Type: application/json" -X DELETE https://java-cf-redis-api/api/key/test
    {
      "result": 1
    }
<!-- Break up the code blocks-->
    $ curl -i -H "Content-Type: application/json" -X GET https://java-cf-redis-api/api/key/test
    HTTP/1.1 404 Not Found
    Date: Wed, 10 Aug 2016 17:23:40 GMT
    X-Vcap-Request-Id: e25668a6-fc1e-470a-7088-1589f907d9cf
    Content-Length: 0
<!-- Break up the code blocks-->
    $ curl -H "Content-Type: application/json" -X DELETE https://java-cf-redis-api/api/key/test
    {
      "result": 0
    }