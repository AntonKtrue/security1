package org.hyperskill.security.controller;

import org.hyperskill.security.domain.Message;
import org.hyperskill.security.domain.ResponseWrapper;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/business")
public class BusinessController {


    @GetMapping("/info")
    public ResponseWrapper getInfo() {
        return ResponseWrapper.data("Hello, world!");
    }

    @PostMapping("/echo")
    public ResponseWrapper echo(Principal principal, @RequestBody Message message) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message.getText() + " from " + principal.getName());
        return ResponseWrapper.data(response);
    }
}
