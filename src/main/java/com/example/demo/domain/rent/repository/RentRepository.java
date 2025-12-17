package com.example.demo.domain.rent.repository;

import com.example.demo.domain.book.entity.Book;
import com.example.demo.domain.rent.entity.mapping.Rent;
import com.example.demo.domain.rent.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentRepository extends JpaRepository<Rent, Long> {
    boolean existsByBookAndStatus(Book book, Status status);
}
