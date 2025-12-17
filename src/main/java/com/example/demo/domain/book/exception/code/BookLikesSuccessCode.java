package com.example.demo.domain.book.exception.code;

import com.example.demo.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BookLikesSuccessCode implements BaseSuccessCode {
    BOOK_LIKE_SUCCESS(HttpStatus.CREATED, "BOOK_201_2", "도서 좋아요 설정이 완료되었습니다."),
    BOOK_UNLIKE_SUCCESS(HttpStatus.OK, "BOOK_200_4", "도서 좋아요 취소가 완료되었습니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
