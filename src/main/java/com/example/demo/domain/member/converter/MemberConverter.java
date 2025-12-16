package com.example.demo.domain.member.converter;

import com.example.demo.domain.member.dto.req.MemberReqDTO;
import com.example.demo.domain.member.dto.res.MemberResDTO;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.global.auth.enums.Role;

public class MemberConverter {
    // DTO -> Entity
    public static Member toMember(MemberReqDTO.JoinDTO dto, String hashedPassword, Role role) {
        return Member.builder()
                .email(dto.email())
                .password(hashedPassword)
                .name(dto.name())
                .gender(dto.gender())
                .role(role)
                .build();
    }

    // Entity -> DTO
    public static MemberResDTO.JoinDto toJoinDTO(Member member) {
        return MemberResDTO.JoinDto.builder()
                .memberId(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }
}
