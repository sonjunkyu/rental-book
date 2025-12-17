package com.example.demo.domain.book.dto.res;

import lombok.Builder;

import java.time.LocalDateTime;
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

    @Builder
    public record BookLikeResult(
            Long bookLikeId,
            Long memberId,
            Long bookId,
            LocalDateTime createdAt
    ) {}

    @Builder
    public record BookUnlikeResult(
            Long bookId,
            Long memberId,
            String bookName,
            String message // "취소됨" 같은 상태 메시지 포함 가능
    ) {}
}
