package com.example.demo.global.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KakaoUserInfoResDTO {
    // 카카오 유저 정보 응답
    public record KakaoUserInfoResponse(
            Long id,
            @JsonProperty("kakao_account") KakaoAccount kakaoAccount
    ) {
        public record KakaoAccount(
                String email,
                Profile profile
        ) {
            public record Profile(
                    String nickname
            ) {}
        }
    }
}
