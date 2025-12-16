package com.example.demo.domain.book.exception.code;

import com.example.demo.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BookSuccessCode implements BaseSuccessCode {
    BOOK_DETAIL_GET_SUCCESS(HttpStatus.OK, "BOOK_200_1", "도서 상세 정보 조회가 완료되었습니다."),
    BOOK_LIST_GET_SUCCESS(HttpStatus.OK, "BOOK_200_2", "도서 목록 조회가 완료되었습니다."),
    BOOK_CREATE_SUCCESS(HttpStatus.CREATED, "BOOK_201_1", "새 도서 정보 등록이 완료되었습니다."),
    BOOK_UPDATE_SUCCESS(HttpStatus.OK, "BOOK_200_3", "도서 정보 수정이 완료되었습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
