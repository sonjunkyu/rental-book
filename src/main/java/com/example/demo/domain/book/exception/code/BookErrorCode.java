package com.example.demo.domain.book.exception.code;

import com.example.demo.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BookErrorCode implements BaseErrorCode {
    BOOK_NOT_FOUND(HttpStatus.NOT_FOUND, "BOOK_404_1", "해당 도서를 찾을 수 없습니다."),
    BOOK_ALREADY_RENTED(HttpStatus.BAD_REQUEST, "BOOK_400_1", "이미 대여 중인 도서입니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
