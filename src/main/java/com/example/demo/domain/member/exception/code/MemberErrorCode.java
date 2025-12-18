package com.example.demo.domain.member.exception.code;

import com.example.demo.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_1", "해당 멤버를 찾을 수 없습니다."),
    INVALID(HttpStatus.BAD_REQUEST, "MEMBER400_1", "비밀번호가 일치하지 않습니다."),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "MEMBER409_1", "이미 사용 중인 이메일입니다."),

    // 리프레시 토큰 관련 에러 코드
    REFRESH_TOKEN_INVALID(HttpStatus.UNAUTHORIZED, "AUTH401_2", "유효하지 않은 리프레시 토큰입니다."),
    REFRESH_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "AUTH401_3", "리프레시 토큰이 만료되었습니다."),
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, "AUTH401_4", "존재하지 않는 리프레시 토큰입니다."); // DB에 없을 때
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
