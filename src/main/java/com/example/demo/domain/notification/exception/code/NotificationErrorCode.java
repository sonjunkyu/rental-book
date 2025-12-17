package com.example.demo.domain.notification.exception.code;

import com.example.demo.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum NotificationErrorCode implements BaseErrorCode {
    NOTIFICATION_NOT_FOUND(HttpStatus.NOT_FOUND, "NOTIFICATION_404_1", "존재하지 않는 알림입니다."),

    NOTIFICATION_ACCESS_DENIED(HttpStatus.FORBIDDEN, "NOTIFICATION_403_1", "해당 알림에 대한 접근 권한이 없습니다."),

    INVALID_NOTIFICATION_REQUEST(HttpStatus.BAD_REQUEST, "NOTIFICATION_400_1", "유효하지 않은 알림 요청 데이터입니다."),

    NOTIFICATION_SEND_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "NOTIFICATION_500_1", "알림 전송에 실패했습니다.");
    ;
    private final HttpStatus status;
    private final String code;
    private final String message;
}
