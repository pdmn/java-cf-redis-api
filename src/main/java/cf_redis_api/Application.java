/*
 * Copyright (c) 2016 Adam Duston
 * License: MIT
 *
 * Application.java contains the main execution code for the java-cf-redis-api app.
 */

package cf_redis_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableWebMvc
public class Application {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
    }

}