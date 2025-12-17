package com.example.demo.domain.book.converter;

import com.example.demo.domain.book.dto.res.BookResDTO;
import com.example.demo.domain.book.entity.Book;
import com.example.demo.domain.book.entity.mapping.BookLikes;
import com.example.demo.domain.member.entity.Member;

public class BookLikesConverter {
    // Entity 생성
    public static BookLikes toBookLikes(Book book, Member member) {
        return BookLikes.builder()
                .book(book)
                .member(member)
                .build();
    }

    // Entity -> DTO
    public static BookResDTO.BookLikeResult toBookLikeResult(BookLikes bookLikes) {
        return BookResDTO.BookLikeResult.builder()
                .bookLikeId(bookLikes.getId())
                .memberId(bookLikes.getMember().getId())
                .bookId(bookLikes.getBook().getId())
                .createdAt(bookLikes.getCreatedAt())
                .build();
    }

    // Entity -> DTO
    public static BookResDTO.BookUnlikeResult toBookUnlikeResult(BookLikes bookLikes) {
        return BookResDTO.BookUnlikeResult.builder()
                .bookId(bookLikes.getBook().getId())
                .memberId(bookLikes.getMember().getId())
                .bookName(bookLikes.getBook().getName())
                .message("좋아요가 취소되었습니다.")
                .build();
    }
}
