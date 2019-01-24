package com.example.lophie.log.web.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
class SystemController {
    /**
     * log
     */
    private final static Logger logger = LoggerFactory.getLogger(SystemController.class);

    @RequestMapping(value = "/hello/{name}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    String hello(@PathVariable String name) {
        logger.info("call hello:" + name);
        return "Hello, " + name + "!";
    }

}
