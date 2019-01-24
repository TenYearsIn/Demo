package com.example.lophie.gateway.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@EnableAutoConfiguration
class SystemController {
    /**
     * log
     */
    private final static Logger logger = LoggerFactory.getLogger(SystemController.class);

    @RequestMapping(value = "/hello/{name}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE )
    @ResponseBody
    Mono<String> hello(@PathVariable String name) {
        logger.info(" call {} ",name);
        return Mono.create(moniSink -> moniSink.success("Hello " + name));
    }

}
