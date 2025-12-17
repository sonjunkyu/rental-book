package com.example.demo.domain.rent.entity.mapping;

import com.example.demo.domain.book.entity.Book;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.rent.enums.Status;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "rent")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rent_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @Builder.Default
    private Status status = Status.NONE;

    // 대여 시작 시간
    @Column(name = "rented_at", nullable = false, columnDefinition = "TIMESTAMP")
    @Builder.Default
    private LocalDateTime rentedAt = LocalDateTime.now(); // 빌더 사용 시 초기값 설정
    
    // 반납 예정일
    @Column(name = "due_at", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime dueAt;
    
    // 반납된 시간
    @Column(name = "returned_at", nullable = true, columnDefinition = "TIMESTAMP")
    private LocalDateTime returnedAt;

    // 반납 처리
    public void returnBook() {
        this.status = Status.RETURNED;
        this.returnedAt = LocalDateTime.now();
    }
}
