package com.example.demo.domain.book.dto.res;

import lombok.Builder;

import java.util.List;

public class BookResDTO {
    @Builder
    public record BookPreViewListDTO(
       List<BookPreViewDTO> bookList,
       Integer listSize,
       Integer totalPage,
       Long totalElements,
       Boolean isFirst,
       Boolean isLast
    ) {}

    @Builder
    public record BookPreViewDTO(
        String name,
        String description
    ) {}
}
