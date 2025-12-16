package com.example.demo.domain.book.service;

import com.example.demo.domain.book.dto.res.BookResDTO;

public interface BookQueryService {
    BookResDTO.BookPreViewListDTO getBookList(Integer page);
}
