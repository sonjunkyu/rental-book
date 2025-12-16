package com.example.demo.domain.book.controller;

import com.example.demo.domain.book.dto.res.BookResDTO;
import com.example.demo.domain.book.exception.code.BookSuccessCode;
import com.example.demo.domain.book.service.BookQueryService;
import com.example.demo.global.annotation.ValidPage;
import com.example.demo.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
public class BookController {
    private final BookQueryService bookQueryService;

    // 도서 목록 조회
    @GetMapping("/books")
    public ApiResponse<BookResDTO.BookPreViewListDTO> getBookList(
            @ValidPage @RequestParam(defaultValue = "1") Integer page
    ) {
        return ApiResponse.onSuccess(BookSuccessCode.BOOK_LIST_GET_SUCCESS,bookQueryService.getBookList(page));
    }
}
