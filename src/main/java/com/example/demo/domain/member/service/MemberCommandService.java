package com.example.demo.domain.member.service;

import com.example.demo.domain.member.dto.req.MemberReqDTO;
import com.example.demo.domain.member.dto.res.MemberResDTO;

public interface MemberCommandService {
    MemberResDTO.JoinDto signUp(MemberReqDTO.JoinDTO dto);

    String logout(String accessToken, String email);
}
