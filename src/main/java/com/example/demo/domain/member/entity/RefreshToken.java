package com.example.demo.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash(value = "refreshToken", timeToLive = 1209600)
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RefreshToken {
    @Id
    private String id;

    @Indexed
    private String token;

    @Indexed
    private String email;

    // 토큰 교체
    public void updateToken(String newToken) {
        this.token = newToken;
    }
}