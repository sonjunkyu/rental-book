package com.example.demo.domain.book.exception;

import com.example.demo.global.apiPayload.code.BaseErrorCode;
import com.example.demo.global.apiPayload.exception.GeneralException;

public class BookLikesException extends GeneralException {
    public BookLikesException(BaseErrorCode code) {
        super(code);
    }
}
