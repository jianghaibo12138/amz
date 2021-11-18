package com.amazing2j.amz.amz_server.controller.welcome;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    @GetMapping(value = "/")
    public String welcome() {
        return "Hello World";
    }
}
