package com.example.demo.domain.notification.exception.code;

import com.example.demo.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum NotificationSuccessCode implements BaseSuccessCode {
    NOTIFICATION_SEND_SUCCESS(HttpStatus.CREATED, "NOTIFICATION_201_1", "알림이 성공적으로 발송되었습니다."),
    NOTIFICATION_CONFIRM_SUCCESS(HttpStatus.OK, "NOTIFICATION_200_1", "알림 확인 처리가 완료되었습니다."),
    NOTIFICATION_LIST_GET_SUCCESS(HttpStatus.OK, "NOTIFICATION_200_2", "알림 목록 조회가 완료되었습니다.");
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
