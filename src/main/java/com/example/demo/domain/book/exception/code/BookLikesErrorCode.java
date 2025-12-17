package com.example.demo.domain.book.exception.code;

import com.example.demo.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BookLikesErrorCode implements BaseErrorCode {
    BOOK_LIKE_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "BOOK_400_3", "이미 좋아요를 누른 도서입니다."),
    BOOK_LIKE_NOT_FOUND(HttpStatus.NOT_FOUND, "BOOK_404_2", "좋아요 기록이 존재하지 않습니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
