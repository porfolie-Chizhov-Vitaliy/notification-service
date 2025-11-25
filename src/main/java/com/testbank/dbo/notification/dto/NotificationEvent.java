package com.testbank.dbo.notification.dto;

public class NotificationEvent {
    private Long paymentId;
    private String type; // "PAYMENT_SUCCESS", "INSUFFICIENT_FUNDS", "ACCOUNT_NOT_FOUND"
    private String message;
    private String title;

    // конструкторы, геттеры, сеттеры
    public NotificationEvent() {
    }

    public NotificationEvent(Long paymentId, String type, String message,String title) {
        this.paymentId = paymentId;
        this.type = type;
        this.message = message;
        this.title=title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
