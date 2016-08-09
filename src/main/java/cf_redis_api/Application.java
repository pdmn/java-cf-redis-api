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
/*
 * Disable security auto configuration for now, or it will require authentication. Should probably do this
 * In a smarter way that only allows specific routes at some point. This works for now.
 */
@EnableAutoConfiguration(exclude={org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}