package com.example.cloudgateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackMethodController {

    @GetMapping("/userServiceFallBackMethod")
    public String userServiceFallBackMethod() {
        return "User service is taking longer than expected. \nPlease try again later.";
    }

    @GetMapping("/gameServiceFallBackMethod")
    public String gameServiceFallBackMethod() {
        return "Game service is taking longer than expected. \nPlease try again later.";
    }

    @GetMapping("/communityServiceFallBackMethod")
    public String communityServiceFallBackMethod() {
        return "Community service is taking longer than expected. \nPlease try again later.";
    }
}
