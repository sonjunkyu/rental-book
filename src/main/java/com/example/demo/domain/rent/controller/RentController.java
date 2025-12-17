package com.example.demo.domain.rent.controller;

import com.example.demo.domain.rent.dto.res.RentResDTO;
import com.example.demo.domain.rent.exception.code.RentSuccessCode;
import com.example.demo.domain.rent.service.RentCommandService;
import com.example.demo.global.apiPayload.ApiResponse;
import com.example.demo.global.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
public class RentController {
    private final RentCommandService rentCommandService;

    // 대출 반납
    @PatchMapping("/rents/{rentId}/return")
    public ApiResponse<RentResDTO.ReturnInfo> returnBook(@PathVariable Long rentId, @AuthenticationPrincipal CustomUserDetails principal) {
        Long memberId = principal.getId();
        return ApiResponse.onSuccess(RentSuccessCode.RENT_RETURN_SUCCESS, rentCommandService.returnBook(rentId, memberId));
    }
}
