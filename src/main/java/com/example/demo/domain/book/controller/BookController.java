package com.example.demo.domain.book.controller;

import com.example.demo.domain.book.dto.res.BookResDTO;
import com.example.demo.domain.book.exception.code.BookLikesSuccessCode;
import com.example.demo.domain.book.exception.code.BookSuccessCode;
import com.example.demo.domain.book.service.BookCommandService;
import com.example.demo.domain.book.service.BookQueryService;
import com.example.demo.domain.rent.dto.req.RentReqDTO;
import com.example.demo.domain.rent.dto.res.RentResDTO;
import com.example.demo.domain.rent.exception.code.RentSuccessCode;
import com.example.demo.domain.rent.service.RentCommandService;
import com.example.demo.global.annotation.ValidPage;
import com.example.demo.global.apiPayload.ApiResponse;
import com.example.demo.global.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
public class BookController {
    private final BookQueryService bookQueryService;
    private final RentCommandService rentCommandService;
    private final BookCommandService bookCommandService;

    // 도서 목록 조회
    @GetMapping("/books")
    public ApiResponse<BookResDTO.BookPreViewListDTO> getBookList(
            @ValidPage @RequestParam(defaultValue = "1") Integer page
    ) {
        return ApiResponse.onSuccess(BookSuccessCode.BOOK_LIST_GET_SUCCESS,bookQueryService.getBookList(page));
    }

    // 도서 대출
    @PostMapping("/books/{bookId}/rent")
    public ApiResponse<RentResDTO.RentInfo> rentBook(@AuthenticationPrincipal CustomUserDetails principal, @RequestBody RentReqDTO.RentCreate dto) {
        Long memberId = principal.getId();
        return ApiResponse.onSuccess(RentSuccessCode.RENT_REQUEST_SUCCESS, rentCommandService.rent(memberId, dto));
    }

    // 사용자 도서 "좋아요" 등록
    @PostMapping("/books{bookId}/likes")
    public ApiResponse<BookResDTO.BookLikeResult> likeBook(@PathVariable Long bookId, @AuthenticationPrincipal CustomUserDetails principal) {
        Long memberId = principal.getId();
        return ApiResponse.onSuccess(BookLikesSuccessCode.BOOK_LIKE_SUCCESS, bookCommandService.likeBook(bookId, memberId));
    }

    // 도서 좋아요 취소
    @DeleteMapping("/books/{bookId}/likes")
    public ApiResponse<BookResDTO.BookUnlikeResult> unlikeBook(@PathVariable Long bookId, @AuthenticationPrincipal CustomUserDetails principal) {
        Long memberId = principal.getId();

        return ApiResponse.onSuccess(BookLikesSuccessCode.BOOK_UNLIKE_SUCCESS, bookCommandService.unlikeBook(bookId, memberId));
    }
}
