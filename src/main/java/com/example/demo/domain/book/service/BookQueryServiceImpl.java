package com.example.demo.domain.book.service;

import com.example.demo.domain.book.converter.BookConverter;
import com.example.demo.domain.book.dto.res.BookResDTO;
import com.example.demo.domain.book.entity.Book;
import com.example.demo.domain.book.exception.BookException;
import com.example.demo.domain.book.exception.code.BookErrorCode;
import com.example.demo.domain.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookQueryServiceImpl implements BookQueryService {
    private static final int DEFAULT_PAGE_SIZE = 5;

    private final BookRepository bookRepository;

    @Override
    public BookResDTO.BookPreViewListDTO getBookList(Integer page) {
        int pageIndex = (page == null ? 1 : page) - 1;  // page는 1부터 받고, 내부에선 0-based로 변환 (page - 1)
        PageRequest pageRequest = PageRequest.of(pageIndex, DEFAULT_PAGE_SIZE);

        Page<Book> result = bookRepository.findAll(pageRequest);

        if (result.isEmpty()) {
            throw new BookException(BookErrorCode.BOOK_NOT_FOUND);
        }

        return BookConverter.toBookPreViewListDTO(result);
    }
}
