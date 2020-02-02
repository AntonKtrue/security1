package org.hyperskill.security.controller;

import org.hyperskill.security.domain.ResponseWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/business")
public class BusinessController {


    @GetMapping("/info")
    public ResponseWrapper getInfo() {
        return ResponseWrapper.data("Hello, world!");
    }
}
