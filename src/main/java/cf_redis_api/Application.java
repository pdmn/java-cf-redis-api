/*
 * Copyright (c) 2016 Adam Duston
 * License: MIT
 *
 * Application.java contains the main execution code for the java-cf-redis-api app.
 */

package cf_redis_api;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

@SpringBootApplication
//Disable auto-config of security features. Should probably set this up in a smarter way in future.
@EnableAutoConfiguration(exclude={org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}