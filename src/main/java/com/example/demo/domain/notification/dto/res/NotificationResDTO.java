package com.example.demo.domain.notification.dto.res;

import com.example.demo.domain.notification.enums.NotificationType;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class NotificationResDTO {
    @Builder
    public record NotificationInfo(
            Long notificationId,
            String title,
            String content,
            NotificationType type,
            Boolean isConfirmed,
            LocalDateTime createdAt
    ) {}

    @Builder
    public record NotificationList(
            List<NotificationInfo> notifications
    ) {}
}
