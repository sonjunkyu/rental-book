package com.example.demo.global.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KakaoTokenResDTO {
    // 카카오 토큰 응답
    public record KakaoTokenResponse(
            @JsonProperty("access_token") String accessToken,
            @JsonProperty("refresh_token") String refreshToken,
            @JsonProperty("token_type") String tokenType,
            @JsonProperty("expires_in") Integer expiresIn
    ) {}
}
