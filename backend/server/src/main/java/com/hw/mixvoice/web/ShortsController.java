package com.hw.mixvoice.web;


import com.hw.mixvoice.service.ShortsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@RequestMapping("/shorts")
@RequiredArgsConstructor
@RestController
@Slf4j
public class ShortsController {
    private ShortsService shortsService;

    @PostMapping("/upload")
    public ResponseEntity uploadShorts(@RequestBody Map<String, Object> requestData) throws IOException {
        log.info("upload data : {}", requestData);

//        shortsService.uploadShorts();

        return ResponseEntity.ok().build();
    }

}
