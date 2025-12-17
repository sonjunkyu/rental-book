package com.example.demo.domain.notification.dto.req;

import com.example.demo.domain.notification.enums.NotificationType;
import lombok.Builder;

public class NotificationReqDTO {
    @Builder
    public record CreateDTO(
            Long memberId,
            String title,
            String content,
            NotificationType type
    ) {}
}
