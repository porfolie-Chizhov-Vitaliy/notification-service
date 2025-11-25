package com.testbank.dbo.notification.service;

import com.testbank.dbo.notification.Repository.NotificationRepository;
import com.testbank.dbo.notification.dto.NotificationEvent;
import com.testbank.dbo.notification.entity.NotificationEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void saveNotification(NotificationEvent event) {
        try {
            NotificationEntity entity = new NotificationEntity();
            entity.setTitle(event.getTitle());
            entity.setMessage(event.getMessage());
            entity.setCreatedAt(LocalDateTime.now());
            entity.setStatus("PENDING");
            /// entity.setSentAt(LocalDateTime.now());

            notificationRepository.save(entity);
            System.out.println("✅ Уведомление сохранено: {}"+ entity.getTitle());

        } catch (Exception e) {
            System.out.println("❌ Ошибка сохранения уведомления: {}"+ e.getMessage());
        }
    }

    private String generateTitle(NotificationEvent event) {
        return String.format("Платеж %d: %s",
                event.getPaymentId(),
                getEventTypeTitle(event.getType()));
    }

    private String getEventTypeTitle(String type) {
        switch (type) {
            case "PAYMENT_SUCCESS":
                return "Успешный платеж";
            case "INSUFFICIENT_FUNDS":
                return "Недостаточно средств";
            case "ACCOUNT_NOT_FOUND":
                return "Счет не найден";
            default:
                return "Уведомление о платеже";
        }
    }

    // Отметить уведомление как отправленное
    public void markAsSent(Long notificationId) {
        notificationRepository.findById(notificationId).ifPresent(notification -> {
            notification.setStatus("SENT");
            notification.setSentAt(LocalDateTime.now());
            notificationRepository.save(notification);
        });
    }

    public List<NotificationEntity> getPendingNotifications() {
        return notificationRepository.findByStatus("PENDING");
    }

}
