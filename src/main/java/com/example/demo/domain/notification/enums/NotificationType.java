package com.example.demo.domain.notification.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NotificationType {
    NEW_BOOK_ARRIVAL("신간 입고"),
    RENTAL_SUCCESS("대여 성공"),
    RENTAL_RETURNED("반납 성공"),
    RENTAL_OVERDUE("반납 연체"),
    RENTAL_DUE_SOON("반납 임박"),
    SYSTEM_NOTICE("시스템 공지");

    private final String description;
}