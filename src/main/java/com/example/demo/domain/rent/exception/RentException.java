package com.example.demo.domain.rent.exception;

import com.example.demo.global.apiPayload.code.BaseErrorCode;
import com.example.demo.global.apiPayload.exception.GeneralException;

public class RentException extends GeneralException {
    public RentException(BaseErrorCode code) {
        super(code);
    }
}
