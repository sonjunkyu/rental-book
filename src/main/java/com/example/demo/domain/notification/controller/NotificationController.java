package com.example.demo.domain.notification.controller;

import com.example.demo.domain.notification.dto.res.NotificationResDTO;
import com.example.demo.domain.notification.exception.code.NotificationSuccessCode;
import com.example.demo.domain.notification.service.NotificationCommandService;
import com.example.demo.global.apiPayload.ApiResponse;
import com.example.demo.global.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationCommandService notificationCommandService;

    // 알림 읽음 처리
    @PatchMapping("/{notificationId}/confirm")
    public ApiResponse<NotificationResDTO.NotificationInfo> confirmNotification(
            @PathVariable Long notificationId,
            @AuthenticationPrincipal CustomUserDetails principal) {

        Long memberId = principal.getId();
        return ApiResponse.onSuccess(NotificationSuccessCode.NOTIFICATION_CONFIRM_SUCCESS, notificationCommandService.confirmNotification(notificationId, memberId));
    }
}
