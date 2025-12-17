package com.example.demo.domain.book.exception;

import com.example.demo.global.apiPayload.code.BaseErrorCode;
import com.example.demo.global.apiPayload.exception.GeneralException;

public class BookException extends GeneralException {
    public BookException(BaseErrorCode code) {
        super(code);
    }
}
