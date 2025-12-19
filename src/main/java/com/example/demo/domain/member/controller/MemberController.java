package com.example.demo.domain.member.controller;

import com.example.demo.domain.member.dto.req.MemberReqDTO;
import com.example.demo.domain.member.dto.res.MemberResDTO;
import com.example.demo.domain.member.exception.code.MemberSuccessCode;
import com.example.demo.domain.member.service.MemberCommandService;
import com.example.demo.domain.member.service.MemberQueryService;
import com.example.demo.global.apiPayload.ApiResponse;
import com.example.demo.global.auth.service.KakaoAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;
    private final KakaoAuthService kakaoAuthService;

    // 회원가입
    @PostMapping("/sign-up")
    public ApiResponse<MemberResDTO.JoinDto> signUp(@RequestBody @Valid MemberReqDTO.JoinDTO dto) {
        return ApiResponse.onSuccess(MemberSuccessCode.REGISTERED, memberCommandService.signUp(dto));
    }

    // 로그인
    @PostMapping("/login")
    public ApiResponse<MemberResDTO.LoginDto> login(@RequestBody @Valid MemberReqDTO.LoginDTO dto) {
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, memberQueryService.login(dto));
    }

    // 토큰 재발급
    @PostMapping("/reissue")
    public ApiResponse<MemberResDTO.LoginDto> reIssue(@RequestHeader("RefreshToken") String refreshToken) {
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, memberQueryService.reIssue(refreshToken));
    }

    // 카카오 로그인
    @GetMapping("/login/oauth2/code/kakao")
    public ApiResponse<MemberResDTO.LoginDto> kakaoLogin(@RequestParam("code") String code) {
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, kakaoAuthService.kakaoLogin(code));
    }
}
