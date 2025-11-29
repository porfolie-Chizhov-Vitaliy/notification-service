package com.testbank.dbo.notification.controller;



import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Health Notification API", description = "Проверка запуска Java-приложения")
public class HealthController {
    @Operation(summary = "Проверка запуска Java-приложения")
    @ApiResponse(responseCode = "200", description = "Сервис запущен")
    @ApiResponse(responseCode = "400", description = "Сервис не запушен")
    @GetMapping("/health")
    public String health() {
        return "notification-service запущен";
    }

}