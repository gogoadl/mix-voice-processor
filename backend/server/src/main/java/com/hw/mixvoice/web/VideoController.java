package com.hw.mixvoice.web;

import com.hw.mixvoice.service.VideoService;
import com.hw.mixvoice.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;


@RequestMapping("/video")
@RequiredArgsConstructor
@RestController
@Slf4j
public class VideoController {
    private final VideoService videoService;
    @GetMapping("/test/save")
    public void saveTest() throws IOException {
        videoService.saveFile(FileUtil.getMultipartFile("D:\\GitRepository_HW\\web\\mix-voice\\backend\\server\\src\\main\\resources\\static\\offspring.wav"));
    }
    @ResponseBody
    @GetMapping("/test/download")
    public ResponseEntity<Resource> downloadTest(@RequestHeader HttpHeaders httpHeaders) throws FileNotFoundException, IOException {
        UrlResource resource = videoService.downloadFile("ts/KakaoTalk_20230301_205552507.m3u8");
        InputStreamResource resource2 = new InputStreamResource((resource.getInputStream()));

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/x-mpegURL"))
                .body(resource2);
    }

    @PostMapping("upload")
    public ResponseEntity uploadVideo(MultipartFile file, HttpServletRequest request) throws IOException {
        log.info("video received {}", file.getOriginalFilename());

        videoService.uploadVideo(file, request.getServletContext().getRealPath("/"));
        return ResponseEntity.ok().build();
    }

}
