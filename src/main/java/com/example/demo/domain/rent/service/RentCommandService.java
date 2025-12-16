package com.example.demo.domain.rent.service;

import com.example.demo.domain.rent.dto.req.RentReqDTO;
import com.example.demo.domain.rent.dto.res.RentResDTO;

public interface RentCommandService {
    RentResDTO.RentInfo rent(Long memberId, RentReqDTO.RentCreate dto);
}
