package com.example.lophie.discovery.web.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
public class SystemController {

    @RequestMapping(value = "/hello/{name}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    String hello(@PathVariable String name) {
        return "Hello, " + name + "!";
    }
}
