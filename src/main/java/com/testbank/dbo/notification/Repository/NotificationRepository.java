package com.testbank.dbo.notification.Repository;

import com.testbank.dbo.notification.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
    List<NotificationEntity> findByStatus(String status);
    List<NotificationEntity> findByStatusOrderByCreatedAtAsc(String status);
}


