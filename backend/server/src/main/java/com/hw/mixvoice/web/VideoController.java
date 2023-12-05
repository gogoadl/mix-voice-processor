package com.hw.mixvoice.web;

import com.hw.mixvoice.service.VideoService;
import com.hw.mixvoice.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;


@RequestMapping("/video")
@RequiredArgsConstructor
@RestController
public class VideoController {
    private final VideoService videoService;

    @GetMapping("/save")
    public void saveTest() throws IOException {
        videoService.saveFile(FileUtil.getMultipartFile("D:\\GitRepository_HW\\web\\mix-voice\\backend\\server\\src\\main\\resources\\static\\offspring.wav"));
    }
    @GetMapping(path = "/downloadtest", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<ResourceRegion> downloadTest(@RequestHeader HttpHeaders httpHeaders) throws FileNotFoundException, IOException {
        UrlResource resource = videoService.downloadFile("offspring.wav");

        ResourceRegion resourceRegion;

        final long chunkSize = 1000000L;
        long contentLength = resource.contentLength();

        Optional<HttpRange> optional = httpHeaders.getRange().stream().findFirst();
        HttpRange httpRange;
        if (optional.isPresent()) {
            httpRange = optional.get();
            long start = httpRange.getRangeStart(contentLength);
            long end = httpRange.getRangeEnd(contentLength);
            long rangeLength = Long.min(chunkSize, end - start + 1);
            resourceRegion = new ResourceRegion(resource, start, rangeLength);
        } else {
            long rangeLength = Long.min(chunkSize, contentLength);
            resourceRegion = new ResourceRegion(resource, 0, rangeLength);
        }

        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                .contentType(MediaTypeFactory.getMediaType(resource).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(resourceRegion);
    }
}
