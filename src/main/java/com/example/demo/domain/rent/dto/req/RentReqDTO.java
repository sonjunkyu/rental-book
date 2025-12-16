package com.example.demo.domain.rent.dto.req;

public class RentReqDTO {
    public record RentCreate(
        Long bookId
    ) {}
}
