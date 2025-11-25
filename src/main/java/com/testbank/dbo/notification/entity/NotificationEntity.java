package com.testbank.dbo.notification.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false, length = 20)
    private String status = "PENDING";  // PENDING, SENT, FAILED

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "sent_at")
    private LocalDateTime sentAt;

    public NotificationEntity() {
        this.createdAt = LocalDateTime.now();
    }

    public NotificationEntity(String title, String message) {
        this();
        this.title = title;
        this.message = message;
    }

    // Метод для отметки отправки
    public void markAsSent() {
        this.status = "SENT";
        this.sentAt = LocalDateTime.now();
    }



    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }
    public LocalDateTime getSentAt() {
        return sentAt;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}