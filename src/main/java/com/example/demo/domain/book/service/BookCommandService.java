package com.example.demo.domain.book.service;

import com.example.demo.domain.book.dto.res.BookResDTO;

public interface BookCommandService {
    BookResDTO.BookLikeResult likeBook(Long bookId, Long memberId);

    BookResDTO.BookUnlikeResult unlikeBook(Long bookId, Long memberId);
}
