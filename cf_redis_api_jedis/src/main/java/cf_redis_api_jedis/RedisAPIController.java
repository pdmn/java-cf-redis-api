/*
 * Copyright (c) 2016 Adam Duston
 * License: MIT
 *
 * RestController routes for the Redis api endpoints.
 */

package cf_redis_api_jedis;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.Map;
import java.util.HashMap;


@RestController
public class RedisAPIController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Resource(name="redisTemplate")
    private ValueOperations<String, String> valueOps;

    @RequestMapping(value="/api/key/{key}", method=RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getKey(@PathVariable String key) {
        Map<String, Object> output = new HashMap<String, Object>();
        Object value = null;

        value = valueOps.get(key);

        // Null value means no key, return 404.
        if (value == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        output.put("value", value);
        return new ResponseEntity<Map>(output, HttpStatus.OK);
    }

    @RequestMapping(value="/api/key/{key}", method={RequestMethod.PUT, RequestMethod.POST},
                    produces = "application/json")
    public ResponseEntity<Map> setKey(@PathVariable String key, @RequestBody Map<String, String> body) {
        Map<String, Object> output = new HashMap<String, Object>();
        String value_data;

        // Get the value from the request body. Make sure it's not null.
        value_data = body.get("value");
        if (value_data == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        valueOps.set(key, value_data);
        output.put("result", true);
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    @RequestMapping(value="/api/key/{key}", method=RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<?> deleteKey(@PathVariable String key) {
        Map<String, Object> output = new HashMap<String, Object>();
        String value;
        int result = 0;

        value = valueOps.get(key);
        // Result 1 if key exists and is deleted. Result 0 if no key to delete.
        if (value != null) {
            valueOps.getOperations().delete(key);
            result = 1;
        }

        output.put("result", result);
        return new ResponseEntity<Map>(output, HttpStatus.OK);
    }
}
