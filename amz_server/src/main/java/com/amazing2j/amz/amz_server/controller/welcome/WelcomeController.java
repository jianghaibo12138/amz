package com.amazing2j.amz.amz_server.controller.welcome;

import com.amazing2j.amz.amz_server.utils.JsonResponse;
import com.amazing2j.amz.amz_server.utils.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    @GetMapping(value = "/")
    public JsonResult welcome() {
        return JsonResponse.getSuccessResult("Welcome to AMZ server.");
    }
}
