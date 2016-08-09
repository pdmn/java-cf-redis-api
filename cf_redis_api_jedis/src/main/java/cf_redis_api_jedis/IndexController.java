/*
 * Copyright (c) 2016 Adam Duston
 * License: MIT
 *
 * Controller for the index.html status page.
 */
package cf_redis_api_jedis;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.ui.Model;

@Controller
public class IndexController {

    @Autowired
    JedisConnectionFactory jedisConnectionFactory;

    @RequestMapping("/")
    public String index(Model model) {
        String operatingSystem = String.format("%s %s", System.getProperty("os.name"),
                                                        System.getProperty("os.version"));
        String redisHost = String.format("%s:%s", jedisConnectionFactory.getHostName(),
                jedisConnectionFactory.getPort());

        model.addAttribute("javaVersion",System.getProperty("java.version"));
        model.addAttribute("javaVendor",System.getProperty("java.vendor"));
        model.addAttribute("javaHome", System.getProperty("java.home"));
        model.addAttribute("classPath", System.getProperty("java.class.path"));
        model.addAttribute("operatingSystem", operatingSystem);
        model.addAttribute("redisHost", redisHost);

        return "index";
    }
}
