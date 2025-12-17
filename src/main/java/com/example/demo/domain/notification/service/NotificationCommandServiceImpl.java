package com.example.demo.domain.notification.service;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.notification.converter.NotificationConverter;
import com.example.demo.domain.notification.dto.req.NotificationReqDTO;
import com.example.demo.domain.notification.dto.res.NotificationResDTO;
import com.example.demo.domain.notification.entity.Notification;
import com.example.demo.domain.notification.exception.NotificationException;
import com.example.demo.domain.notification.exception.code.NotificationErrorCode;
import com.example.demo.domain.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class NotificationCommandServiceImpl implements NotificationCommandService {
    private final NotificationRepository notificationRepository;

    // 알림 생성
    @Override
    public void createNotification(Member member, NotificationReqDTO.CreateDTO dto) {
        Notification notification = NotificationConverter.toNotification(member, dto);
        notificationRepository.save(notification);
    }

    // 알림 읽음 처리
    @Override
    public NotificationResDTO.NotificationInfo confirmNotification(Long notificationId, Long memberId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new NotificationException(NotificationErrorCode.NOTIFICATION_NOT_FOUND));

        // 권한 검증 (내 알림인지)
        if (!notification.getMember().getId().equals(memberId)) {
            throw new NotificationException(NotificationErrorCode.NOTIFICATION_ACCESS_DENIED);
        }

        notification.confirm(); // 상태 변경

        return NotificationConverter.toNotificationInfo(notification);
    }
}
