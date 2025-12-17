package com.example.demo.domain.member.service;

import com.example.demo.domain.member.dto.req.MemberReqDTO;
import com.example.demo.domain.member.dto.res.MemberResDTO;
import jakarta.validation.Valid;

public interface MemberQueryService {
    MemberResDTO.LoginDto login(MemberReqDTO.@Valid LoginDTO dto);
}
