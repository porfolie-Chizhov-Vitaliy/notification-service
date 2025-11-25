package com.testbank.dbo.notification.controller;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health")
    public String health() {
        return "Notification Service is UP and running!";
    }

    @GetMapping("/api/notifications/hello")
    public String hello() {
        return "Hello from Notification Service!";
    }
}