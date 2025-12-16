package com.example.demo.domain.member.dto.res;

import lombok.Builder;

import java.time.LocalDateTime;

public class MemberResDTO {
    @Builder
    public record JoinDto(
            Long memberId,
            LocalDateTime createdAt
    ) {}
}
