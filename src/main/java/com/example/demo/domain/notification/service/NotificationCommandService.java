package com.example.demo.domain.notification.service;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.notification.dto.req.NotificationReqDTO;
import com.example.demo.domain.notification.dto.res.NotificationResDTO;

public interface NotificationCommandService {
    void createNotification(Member member, NotificationReqDTO.CreateDTO dto);

    NotificationResDTO.NotificationInfo confirmNotification(Long notificationId, Long memberId);
}
