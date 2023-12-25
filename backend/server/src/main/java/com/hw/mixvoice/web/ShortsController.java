package com.hw.mixvoice.web;


import com.hw.mixvoice.domain.shorts.Shorts;
import com.hw.mixvoice.service.ShortsService;
import com.hw.mixvoice.web.dto.ShortsSaveDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RequestMapping("/shorts")
@RequiredArgsConstructor
@RestController
@Slf4j
public class ShortsController {

    @Autowired
    private ShortsService shortsService;
    private final String REDIRECT_URL = "http://localhost:3000/";

    @GetMapping("/")
    public ResponseEntity getRandomShorts() {

        Optional<Shorts> shorts = shortsService.getShorts();
        return ResponseEntity.ok().body(shorts);
    }

    @PostMapping("/upload")
    public ResponseEntity uploadShorts(@RequestBody Map<String, String> requestData) {
        log.info("upload data : {}", requestData);
        String[] path = requestData.get("path").split("\\."); // 확장자 떼기
        ShortsSaveDto shortsSaveDto = new ShortsSaveDto(requestData.get("title"), requestData.get("content"), path[0]);

        shortsService.uploadShorts(shortsSaveDto);
        return ResponseEntity.ok().build();
    }

}
