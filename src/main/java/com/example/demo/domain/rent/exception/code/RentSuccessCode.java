package com.example.demo.domain.rent.exception.code;

import com.example.demo.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum RentSuccessCode implements BaseSuccessCode {
    RENT_REQUEST_SUCCESS(HttpStatus.CREATED, "RENT_201_1", "도서 대여가 완료되었습니다."),
    RENT_RETURN_SUCCESS(HttpStatus.OK, "RENT_200_1", "도서 반납이 완료되었습니다."),
    RENT_EXTENSION_SUCCESS(HttpStatus.OK, "RENT_200_2", "대여 기간 연장이 완료되었습니다."),
    RENT_HISTORY_CHECK_SUCCESS(HttpStatus.OK, "RENT_200_3", "대여 이력 조회가 완료되었습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
