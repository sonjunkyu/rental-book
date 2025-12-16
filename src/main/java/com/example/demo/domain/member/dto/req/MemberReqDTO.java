package com.example.demo.domain.member.dto.req;

import com.example.demo.domain.member.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MemberReqDTO {
    // 회원가입
    public record JoinDTO(
        @Email(message = "올바른 이메일 형식이 아닙니다.")
        String email,

        @NotBlank(message = "비밀번호를 입력해주세요.")
        String password,

        @NotBlank(message = "이름을 입력해주세요.")
        String name,

        @NotNull(message = "성별을 선택해주세요.")
        Gender gender
    ) {}
}
