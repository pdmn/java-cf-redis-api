/*
 * Copyright (c) 2016 Adam Duston
 * License: MIT
 *
 * Setup the redis connection details.
 *

package cf_redis_api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;


@Configuration
@Profile("cloud")
public class RedisConfiguration extends AbstractCloudConfig {

    @Bean
    public Cloud cloud() {
        return new CloudFactory().getCloud();
    }

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return cloud().getSingletonServiceConnector(JedisConnectionFactory.class, null);
    }

    @Bean
    RedisTemplate redisTemplate() {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }
}
*/