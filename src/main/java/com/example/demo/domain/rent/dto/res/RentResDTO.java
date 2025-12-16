package com.example.demo.domain.rent.dto.res;

import lombok.Builder;

import java.time.LocalDateTime;

public class RentResDTO {
    @Builder
    public record RentInfo(
       Long rentId,
       String bookName,
       LocalDateTime rentedAt,
       LocalDateTime dueAt
    ) {}

    @Builder
    public record ReturnInfo(
       Long rentId,
       String bookName,
       LocalDateTime returnedAt
    ) {}
}
