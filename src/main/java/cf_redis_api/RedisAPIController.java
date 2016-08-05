/*
 * Copyright (c) 2016 Adam Duston
 * License: MIT
 *
 * RestController routes for the Redis api endpoints.
 */

package cf_redis_api;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.Map;
import java.util.HashMap;


@RestController
public class RedisAPIController {
    private Map<String,String> testValues = new HashMap<String, String>();

    @Autowired
    private void createTestValues() {
        testValues.put("test", "hello");
        testValues.put("animal", "puppy");
        testValues.put("two_plus_two", "threeve");
        testValues.put("abcd", "wxyz");
    }

    @RequestMapping(value="/api/key/{key}", method=RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getKey(@PathVariable String key) {
        Map<String, Object> output = new HashMap<String, Object>();

        String value = testValues.get(key);
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
        String value = body.get("value");

        if (value == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        testValues.put(key, value);
        output.put("result", true);
        return new ResponseEntity<Map>(output, HttpStatus.OK);
    }

    @RequestMapping(value="/api/key/{key}", method=RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<?> deleteKey(@PathVariable String key) {
        Map<String, Object> output = new HashMap<String, Object>();
        String result;

        result = testValues.remove(key);
        output.put("result", result);
        return new ResponseEntity<Map>(output, HttpStatus.OK);
    }
}
