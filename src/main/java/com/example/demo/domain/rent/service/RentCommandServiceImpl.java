package com.example.demo.domain.rent.service;

import com.example.demo.domain.book.entity.Book;
import com.example.demo.domain.book.exception.BookException;
import com.example.demo.domain.book.exception.code.BookErrorCode;
import com.example.demo.domain.book.repository.BookRepository;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.exception.MemberException;
import com.example.demo.domain.member.exception.code.MemberErrorCode;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.rent.converter.RentConverter;
import com.example.demo.domain.rent.dto.req.RentReqDTO;
import com.example.demo.domain.rent.dto.res.RentResDTO;
import com.example.demo.domain.rent.entity.mapping.Rent;
import com.example.demo.domain.rent.enums.Status;
import com.example.demo.domain.rent.exception.RentException;
import com.example.demo.domain.rent.exception.code.RentErrorCode;
import com.example.demo.domain.rent.repository.RentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RentCommandServiceImpl implements RentCommandService {
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final RentRepository rentRepository;
    private static final int RENT_PERIOD_DAYS = 14; // 대출 기간

    @Override
    @Transactional
    public RentResDTO.RentInfo rent(Long memberId, RentReqDTO.RentCreate dto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Book book = bookRepository.findById(dto.bookId())
                .orElseThrow(() -> new BookException(BookErrorCode.BOOK_NOT_FOUND));

        // 중복 대출 방지
        boolean alreadyRented = rentRepository.existsByBookAndStatus(book, Status.RENTED);
        if (alreadyRented) {
            throw new RentException(RentErrorCode.RENT_ALREADY_EXISTS);
        }

        LocalDateTime rentedAt = LocalDateTime.now();
        LocalDateTime dueAt = rentedAt.plusDays(RENT_PERIOD_DAYS);

        Rent rent = RentConverter.toRent(member, book, rentedAt, dueAt);
        rentRepository.save(rent);

        return RentConverter.toRentInfo(rent);
    }
}
