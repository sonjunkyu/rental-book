package com.example.demo.domain.notification.exception;

import com.example.demo.global.apiPayload.code.BaseErrorCode;
import com.example.demo.global.apiPayload.exception.GeneralException;

public class NotificationException extends GeneralException {
    public NotificationException(BaseErrorCode code) {
        super(code);
    }
}
