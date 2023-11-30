package com.hw.mixvoice.web;

import com.hw.mixvoice.config.auth.LoginUser;
import com.hw.mixvoice.config.auth.dto.SessionUser;
import com.hw.mixvoice.service.PostsService;
import com.hw.mixvoice.service.VideoService;
import com.hw.mixvoice.util.FileUtil;
import com.hw.mixvoice.web.dto.PostsResponseDto;
import com.hw.mixvoice.web.dto.PostsSaveRequestDto;
import com.hw.mixvoice.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@RequestMapping("/video")
@RequiredArgsConstructor
@RestController
public class VideoController {
    private final VideoService videoService;

    @GetMapping("/test")
    public void test() throws IOException // Model - 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있다.
    {
        videoService.saveFile(FileUtil.getMultipartFile("D:\\GitRepository_HW\\web\\mix-voice\\backend\\server\\src\\main\\resources\\static\\test.mp4"));
    }
}
