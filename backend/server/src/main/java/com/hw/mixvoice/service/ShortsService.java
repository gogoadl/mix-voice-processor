package com.hw.mixvoice.service;

import com.hw.mixvoice.domain.shorts.ShortsRepository;
import com.hw.mixvoice.web.dto.ShortsSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor // final이 선언된 모든 필드를 인자값으로 하는 생성자를 만들어 줌
@Service
public class ShortsService {
    private ShortsRepository shortsRepository;

    @Transactional
    public void uploadShorts(ShortsSaveDto shortsSaveDto) {
        shortsRepository.save(shortsSaveDto.toEntity());
    }
}