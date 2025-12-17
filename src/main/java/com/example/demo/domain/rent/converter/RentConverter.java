package com.example.demo.domain.rent.converter;

import com.example.demo.domain.book.entity.Book;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.rent.dto.res.RentResDTO;
import com.example.demo.domain.rent.entity.mapping.Rent;
import com.example.demo.domain.rent.enums.Status;

import java.time.LocalDateTime;

public class RentConverter {
    // DTO -> Entity
    public static Rent toRent(Member member, Book book, LocalDateTime rentedAt, LocalDateTime dueAt) {
        return Rent.builder()
                .member(member)
                .book(book)
                .rentedAt(rentedAt)
                .dueAt(dueAt)
                .status(Status.RENTED)
                .build();
    }

    // Entity -> DTO
    public static RentResDTO.RentInfo toRentInfo(Rent rent) {
        return RentResDTO.RentInfo.builder()
                .rentId(rent.getId())
                .bookName(rent.getBook().getName())
                .rentedAt(rent.getRentedAt())
                .dueAt(rent.getDueAt())
                .build();
    }

    public static RentResDTO.ReturnInfo toReturnInfo(Rent rent) {
        return RentResDTO.ReturnInfo.builder()
                .rentId(rent.getId())
                .bookName(rent.getBook().getName())
                .returnedAt(rent.getReturnedAt())
                .build();
    }
}
