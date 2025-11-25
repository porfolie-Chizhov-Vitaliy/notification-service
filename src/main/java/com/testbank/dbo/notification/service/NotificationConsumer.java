package com.testbank.dbo.notification.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testbank.dbo.notification.dto.BalancePaymentResult;
import com.testbank.dbo.notification.dto.NotificationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {
    private final ObjectMapper objectMapper;
    @Autowired
    private NotificationService notificationService;

    public NotificationConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "notify-events", groupId = "notification-service")
    public void consumeNotification(String message) {  // ← Принимаем String!
        try {
            System.out.println("🎯 СЫРОЕ СООБЩЕНИЕ: " + message);

            // Парсим JSON вручную
            BalancePaymentResult balanceResult = objectMapper.readValue(message, BalancePaymentResult.class);

            System.out.println("📨 Получен платеж: " + balanceResult.getPaymentId() + ", статус: " + balanceResult.getStatus());

            NotificationEvent clientNotification = transformToClientNotification(balanceResult);
            System.out.println("type:"+clientNotification.getType()+"title:"+clientNotification.getTitle()+"message:"+clientNotification.getMessage() );
            notificationService.saveNotification(clientNotification);

            System.out.println("✅ Уведомление сохранено для платежа: " + balanceResult.getPaymentId());

        } catch (Exception e) {
            System.out.println("❌ Ошибка обработки: " + e);
            e.printStackTrace();
        }
    }

    private NotificationEvent transformToClientNotification(BalancePaymentResult balanceResult) {
        NotificationEvent notification = new NotificationEvent();
        notification.setPaymentId(balanceResult.getPaymentId());

        if (balanceResult.isSuccess()) {
            notification.setType("PAYMENT_SUCCESS");
            notification.setTitle("Платеж выполнен");
            notification.setMessage(String.format(
                    "Ваш платеж на сумму %s RUB успешно обработан. Со счета %s → на счет %s",
                    balanceResult.getAmount(),
                    balanceResult.getFromAccount(),
                    balanceResult.getToAccount()
            ));
        } else {
            notification.setType("PAYMENT_FAILED");
            notification.setTitle("Ошибка перевода");
            notification.setMessage("Платеж не выполнен-"+balanceResult.getMessage());
        }

        return notification;
    }
}
