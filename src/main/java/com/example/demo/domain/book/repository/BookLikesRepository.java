package com.example.demo.domain.book.repository;

import com.example.demo.domain.book.entity.Book;
import com.example.demo.domain.book.entity.mapping.BookLikes;
import com.example.demo.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookLikesRepository extends JpaRepository<BookLikes, Long> {
    // 이미 좋아요 눌렀는지 확인
    boolean existsByBookAndMember(Book book, Member member);

    // 취소를 위해 좋아요 엔티티 조회
    Optional<BookLikes> findByBookAndMember(Book book, Member member);
}
