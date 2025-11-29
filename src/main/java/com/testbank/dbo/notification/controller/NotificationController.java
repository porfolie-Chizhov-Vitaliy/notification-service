package com.testbank.dbo.notification.controller;
import com.testbank.dbo.notification.Repository.NotificationRepository;
import com.testbank.dbo.notification.dto.*;
import com.testbank.dbo.notification.entity.NotificationEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@Tag(name = "Notification API", description = "Операции с уведомлениями")
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationRepository notificationRepository;
    @Operation(summary = "Create notification", description = "Создание уведомление")
    @ApiResponse(responseCode = "200", description = "Успешно создано уведомление")
    @PostMapping
    public NotificationResponse createNotification(@RequestBody NotificationRequest request) {
        NotificationEntity notification = new NotificationEntity();
        notification.setTitle(request.getTitle());
        notification.setMessage(request.getMessage());


        NotificationEntity savedNotification = notificationRepository.save(notification);
        return convertToResponse(savedNotification);
    }
    @Operation(summary = "Get All Notifications", description = "Показать все уведомления")
    @ApiResponse(responseCode = "200", description = "Отображение всех уведомление")
    @GetMapping
    public List<NotificationResponse> getAllNotifications() {
        return notificationRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    @Operation(summary = "Get Notification where id", description = "Показать уведомление по id")
    @ApiResponse(responseCode = "200", description = "Отображение уведомление по id")
    @GetMapping("/{id}")
    public NotificationResponse getNotification(@PathVariable Long id) {
        return notificationRepository.findById(id)
                .map(this::convertToResponse)
                .orElse(null);
    }
    @Operation(summary = "Put Notification mark-sent", description = "Изменить статус на SENT")
    @ApiResponse(responseCode = "200", description = "Изменено уведомление на статус SENT")
    // Метод для пометки уведомления как отправленного
    @PutMapping("/{id}/mark-sent")
    public NotificationResponse markAsSent(@PathVariable Long id) {
        NotificationEntity notification = notificationRepository.findById(id).orElse(null);
        if (notification != null) {
            notification.markAsSent();
            notificationRepository.save(notification);
        }
        return convertToResponse(notification);
    }

    private NotificationResponse convertToResponse(NotificationEntity notification) {
        NotificationResponse response = new NotificationResponse();
        response.setId(notification.getId());
        response.setTitle(notification.getTitle());
        response.setMessage(notification.getMessage());
        response.setStatus(notification.getStatus());
        response.setCreatedAt(notification.getCreatedAt());
        response.setSentAt(notification.getSentAt());
        return response;
    }

    @GetMapping("/simple-test")
    public String simpleTest() {
        return "Simple test works! " ;
    }

}
