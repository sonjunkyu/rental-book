package com.example.demo.domain.rent.exception.code;

import com.example.demo.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum RentErrorCode implements BaseErrorCode {
    RENT_NOT_FOUND(HttpStatus.NOT_FOUND, "RENT_404_1", "존재하지 않는 대여 기록입니다."),
    RENT_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "RENT_400_1", "해당 도서는 이미 대여 중입니다."),
    RENT_COUNT_EXCEEDED(HttpStatus.BAD_REQUEST, "RENT_400_2", "대여 가능한 최대 권수를 초과했습니다."),
    RENT_BLOCKED_BY_OVERDUE(HttpStatus.BAD_REQUEST, "RENT_400_3", "연체된 도서가 있어 신규 대여가 불가능합니다."),
    RENT_ALREADY_RETURNED(HttpStatus.BAD_REQUEST, "RENT_400_4", "이미 반납 처리된 도서입니다."),
    RENT_EXTENSION_LIMIT_EXCEEDED(HttpStatus.BAD_REQUEST, "RENT_400_5", "더 이상 대여 기간을 연장할 수 없습니다."),
    RENT_NOT_AUTHORIZED(HttpStatus.FORBIDDEN, "RENT_403_1", "해당 대여 기록에 대한 접근 권한이 없습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
