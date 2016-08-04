/*
Copyright (c) 2016 Adam Duston
License: MIT

Application.java contains the main execution code for the java-cf-redis-api app.
 */

package java-cf-redis-api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}