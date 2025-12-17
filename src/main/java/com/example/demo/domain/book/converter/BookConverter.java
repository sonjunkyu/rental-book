package com.example.demo.domain.book.converter;

import com.example.demo.domain.book.dto.res.BookResDTO;
import com.example.demo.domain.book.entity.Book;
import org.springframework.data.domain.Page;

public class BookConverter {
    public static BookResDTO.BookPreViewListDTO toBookPreViewListDTO(
            Page<Book> result
    ) {
        return BookResDTO.BookPreViewListDTO.builder()
                .bookList(result.getContent().stream()
                        .map(BookConverter::toBookPreViewDTO).toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static BookResDTO.BookPreViewDTO toBookPreViewDTO(Book book) {
        return BookResDTO.BookPreViewDTO.builder()
                .name(book.getName())
                .description(book.getDescription())
                .build();
    }
}
