package com.example.demo.domain.notification.converter;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.notification.dto.req.NotificationReqDTO;
import com.example.demo.domain.notification.dto.res.NotificationResDTO;
import com.example.demo.domain.notification.entity.Notification;

import java.util.List;
import java.util.stream.Collectors;

public class NotificationConverter {
    // Entity 생성
    public static Notification toNotification(Member member, NotificationReqDTO.CreateDTO dto) {
        return Notification.builder()
                .member(member)
                .title(dto.title())
                .content(dto.content())
                .notificationType(dto.type())
                .isConfirmed(false)
                .build();
    }

    // Entity -> DTO
    public static NotificationResDTO.NotificationInfo toNotificationInfo(Notification notification) {
        return NotificationResDTO.NotificationInfo.builder()
                .notificationId(notification.getId())
                .title(notification.getTitle())
                .content(notification.getContent())
                .type(notification.getNotificationType())
                .isConfirmed(notification.getIsConfirmed())
                .createdAt(notification.getCreatedAt())
                .build();
    }

    // List 변환
    public static NotificationResDTO.NotificationList toNotificationList(List<Notification> notifications) {
        List<NotificationResDTO.NotificationInfo> infoList = notifications.stream()
                .map(NotificationConverter::toNotificationInfo)
                .collect(Collectors.toList());

        return NotificationResDTO.NotificationList.builder()
                .notifications(infoList)
                .build();
    }
}
