package com.example.demo.domain.notification.repository;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    // 특정 회원의 알림 최신순 조회
    List<Notification> findAllByMemberOrderByCreatedAtDesc(Member member);

    // 특정 회원의 읽지 않은 알림만 조회
    List<Notification> findAllByMemberAndIsConfirmedFalseOrderByCreatedAtDesc(Member member);
}
