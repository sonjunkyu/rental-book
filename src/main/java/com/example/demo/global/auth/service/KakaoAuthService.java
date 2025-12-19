package com.example.demo.global.auth.service;

import com.example.demo.domain.member.converter.MemberConverter;
import com.example.demo.domain.member.dto.res.MemberResDTO;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.entity.RefreshToken;
import com.example.demo.domain.member.enums.Gender;
import com.example.demo.domain.member.enums.SocialType;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.member.repository.RefreshTokenRepository;
import com.example.demo.global.auth.CustomUserDetails;
import com.example.demo.global.auth.dto.KakaoTokenResDTO;
import com.example.demo.global.auth.dto.KakaoUserInfoResDTO;
import com.example.demo.global.auth.enums.Role;
import com.example.demo.global.auth.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KakaoAuthService {
    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String redirectUri;

    private final RestClient restClient = RestClient.create();

    @Transactional
    public MemberResDTO.LoginDto kakaoLogin(String code) {
        // 인가 코드로 카카오 엑세스 토큰 요청
        String kakaoAccessToken = getKakaoAccessToken(code);

        // 카카오 엑세스 토큰으로 유저 정보 요청
        KakaoUserInfoResDTO.KakaoUserInfoResponse userInfo = getKakaoUserInfo(kakaoAccessToken);

        // 회원가입, 로그인 처리
        String kakaoEmail = userInfo.kakaoAccount().email();
        String nickname = userInfo.kakaoAccount().profile().nickname();
        String socialId = String.valueOf(userInfo.id());

        // 이메일이 없는 경우(동의 안함 등) 처리가 필요하지만 여기선 socialId로 대체하거나 예외처리
        String email = (kakaoEmail != null) ? kakaoEmail : socialId + "@kakao.com";

        Member member = memberRepository.findByEmail(email)
                .orElseGet(() -> joinKakaoMember(email, nickname, socialId));

        // 서비스 자체 JWT 토큰 발급
        CustomUserDetails userDetails = new CustomUserDetails(member);
        String accessToken = jwtUtil.createAccessToken(userDetails);
        String refreshToken = jwtUtil.createRefreshToken(userDetails);

        RefreshToken refreshTokenEntity = refreshTokenRepository.findByEmail(member.getEmail())
                .orElse(RefreshToken.builder().email(member.getEmail()).token(refreshToken).build());
        refreshTokenEntity.updateToken(refreshToken);
        refreshTokenRepository.save(refreshTokenEntity);

        return MemberConverter.toLoginDto(member, accessToken, refreshToken);
    }

    private String getKakaoAccessToken(String code) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);

        KakaoTokenResDTO.KakaoTokenResponse response = restClient.post()
                .uri("https://kauth.kakao.com/oauth/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(params)
                .retrieve()
                .body(KakaoTokenResDTO.KakaoTokenResponse.class);

        return response.accessToken();
    }

    private KakaoUserInfoResDTO.KakaoUserInfoResponse getKakaoUserInfo(String accessToken) {
        return restClient.get()
                .uri("https://kapi.kakao.com/v2/user/me")
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .body(KakaoUserInfoResDTO.KakaoUserInfoResponse.class);
    }

    private Member joinKakaoMember(String email, String nickname, String socialId) {
        // 비밀번호는 소셜 로그인이라 없지만, DB 제약조건(NOT NULL) 때문에 임의의 값 설정
        String randomPassword = passwordEncoder.encode(UUID.randomUUID().toString());

        Member member = Member.builder()
                .email(email)
                .password(randomPassword)
                .name(nickname)
                .gender(Gender.NONE)
                .role(Role.ROLE_USER)
                .socialType(SocialType.KAKAO)
                .socialId(socialId)
                .build();

        return memberRepository.save(member);
    }
}
