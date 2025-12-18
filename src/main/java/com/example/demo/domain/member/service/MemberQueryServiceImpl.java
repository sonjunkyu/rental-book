package com.example.demo.domain.member.service;

import com.example.demo.domain.member.converter.MemberConverter;
import com.example.demo.domain.member.dto.req.MemberReqDTO;
import com.example.demo.domain.member.dto.res.MemberResDTO;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.entity.RefreshToken;
import com.example.demo.domain.member.exception.MemberException;
import com.example.demo.domain.member.exception.code.MemberErrorCode;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.member.repository.RefreshTokenRepository;
import com.example.demo.global.auth.CustomUserDetails;
import com.example.demo.global.auth.jwt.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQueryService {
    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    // 로그인 (AccessToken + RefreshToken 발급)
    @Transactional
    public MemberResDTO.LoginDto login(MemberReqDTO.@Valid LoginDTO dto) {
        // Member 조회
        Member member = memberRepository.findByEmail(dto.email())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 비밀번호 검증
        if (!passwordEncoder.matches(dto.password(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.INVALID);
        }

        // JWT 토큰 발급용 UserDetails
        CustomUserDetails userDetails = new CustomUserDetails(member);

        // 엑세스 토큰 발급
        String accessToken = jwtUtil.createAccessToken(userDetails);

        // RefreshToken 발급
        String refreshToken = jwtUtil.createRefreshToken(userDetails);

        // RefreshToken DB 저장 (기존 것 있으면 업데이트, 없으면 생성)
        RefreshToken refreshTokenEntity = refreshTokenRepository.findByEmail(member.getEmail())
                .orElse(RefreshToken.builder().email(member.getEmail()).token(refreshToken).build());

        refreshTokenEntity.updateToken(refreshToken);
        refreshTokenRepository.save(refreshTokenEntity);

        // DTO 조립
        return MemberConverter.toLoginDto(member, accessToken, refreshToken);
    }

    // 토큰 재발급
    @Transactional
    public MemberResDTO.LoginDto reIssue(String refreshToken) {
        // RefreshToken 검증
        if (!jwtUtil.isValid(refreshToken)) {
            throw new MemberException(MemberErrorCode.REFRESH_TOKEN_INVALID);
        }

        // DB 에서 토큰 찾기
        RefreshToken savedToken = refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(() -> new MemberException(MemberErrorCode.REFRESH_TOKEN_NOT_FOUND));

        // 유저 찾기
        Member member = memberRepository.findByEmail(savedToken.getEmail())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        CustomUserDetails userDetails = new CustomUserDetails(member);

        // 새 토큰 발급
        String newAccessToken = jwtUtil.createAccessToken(userDetails);
        String newRefreshToken = jwtUtil.createRefreshToken(userDetails);

        // DB 업데이트
        savedToken.updateToken(newRefreshToken);

        return MemberConverter.toLoginDto(member, newAccessToken, newRefreshToken);
    }
}
