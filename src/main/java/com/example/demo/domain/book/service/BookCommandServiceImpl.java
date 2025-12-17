package com.example.demo.domain.book.service;

import com.example.demo.domain.book.converter.BookLikesConverter;
import com.example.demo.domain.book.dto.res.BookResDTO;
import com.example.demo.domain.book.entity.Book;
import com.example.demo.domain.book.entity.mapping.BookLikes;
import com.example.demo.domain.book.exception.BookException;
import com.example.demo.domain.book.exception.BookLikesException;
import com.example.demo.domain.book.exception.code.BookErrorCode;
import com.example.demo.domain.book.exception.code.BookLikesErrorCode;
import com.example.demo.domain.book.repository.BookLikesRepository;
import com.example.demo.domain.book.repository.BookRepository;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.exception.MemberException;
import com.example.demo.domain.member.exception.code.MemberErrorCode;
import com.example.demo.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BookCommandServiceImpl implements BookCommandService {
    private final BookRepository bookRepository;
    private final BookLikesRepository bookLikesRepository;
    private final MemberRepository memberRepository;

    // 도서 좋아요
    @Override
    public BookResDTO.BookLikeResult likeBook(Long bookId, Long memberId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookException(BookErrorCode.BOOK_NOT_FOUND));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 중복 좋아요 검증
        if (bookLikesRepository.existsByBookAndMember(book, member)) {
            throw new BookLikesException(BookLikesErrorCode.BOOK_LIKE_ALREADY_EXISTS);
        }

        // 저장
        BookLikes bookLikes = BookLikesConverter.toBookLikes(book, member);
        bookLikesRepository.save(bookLikes);

        return BookLikesConverter.toBookLikeResult(bookLikes);
    }

    // 도서 좋아요 취소
    @Override
    public BookResDTO.BookUnlikeResult unlikeBook(Long bookId, Long memberId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookException(BookErrorCode.BOOK_NOT_FOUND));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 2. 좋아요 기록 조회 (없으면 예외 발생)
        BookLikes bookLikes = bookLikesRepository.findByBookAndMember(book, member)
                .orElseThrow(() -> new BookLikesException(BookLikesErrorCode.BOOK_LIKE_NOT_FOUND));

        // 3. 삭제
        bookLikesRepository.delete(bookLikes);

        return BookLikesConverter.toBookUnlikeResult(bookLikes);
    }
}
