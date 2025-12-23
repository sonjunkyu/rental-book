package com.example.demo.domain.member.service;

import com.example.demo.domain.member.converter.MemberConverter;
import com.example.demo.domain.member.dto.req.MemberReqDTO;
import com.example.demo.domain.member.dto.res.MemberResDTO;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.exception.MemberException;
import com.example.demo.domain.member.exception.code.MemberErrorCode;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.member.repository.RefreshTokenRepository;
import com.example.demo.global.auth.enums.Role;
import com.example.demo.global.auth.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberCommandServiceImpl implements MemberCommandService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    private final RefreshTokenRepository refreshTokenRepository;
    private final StringRedisTemplate redisTemplate;
    private final JwtUtil jwtUtil;

    // 회원가입
    @Override
    @Transactional
    public MemberResDTO.JoinDto signUp(MemberReqDTO.JoinDTO dto) {
        // 이메일 중복 여부 확인
        if (memberRepository.existsByEmail(dto.email())) {
            throw new MemberException(MemberErrorCode.DUPLICATE_EMAIL);
        }

        String hashedPassword = passwordEncoder.encode(dto.password());
        Member member = MemberConverter.toMember(dto, hashedPassword, Role.ROLE_USER);
        memberRepository.save(member);

        return MemberConverter.toJoinDTO(member);
    }

    // 로그아웃
    @Override
    public String logout(String accessToken, String email) {
        // RefreshToken 삭제
        refreshTokenRepository.findByEmail(email)
                .ifPresent(refreshTokenRepository::delete);

        // AccessToken 블랙리스트 등록
        Long expiration = jwtUtil.getExpiration(accessToken);

        if (expiration > 0) {
            redisTemplate.opsForValue()
                    .set("blacklist:" + accessToken, "logout", expiration, TimeUnit.MILLISECONDS);
        }

        return "로그아웃 되었습니다.";
    }
}
