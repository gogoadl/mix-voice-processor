package com.hw.mixvoice.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.hw.mixvoice.util.FFMPEGProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@Slf4j
public class VideoService  {

    @Autowired
    AmazonS3 amazonS3;
    @Autowired
    FFMPEGProcessor ffmpegProcessor;

    @Value("${cloud.aws.bucket}")
    private String bucket;

    public String saveFile(MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        metadata.setContentType(multipartFile.getContentType());

        amazonS3.putObject(bucket, originalFilename, multipartFile.getInputStream(), metadata);
        return amazonS3.getUrl(bucket, originalFilename).toString();
    }

    public UrlResource downloadFile(String originalFilename) {
        return new UrlResource(amazonS3.getUrl(bucket, originalFilename));
    }

    public String uploadVideo(MultipartFile file, String path) throws IOException {
        // 1. 영상 임시 저장 (FFMPEG Java Wrapper Library에서 Stream 타입의 영상을 지원하지 않음)
        String fileRandomName = UUID.randomUUID().toString();
        String tempFilePath = path + fileRandomName;
        file.transferTo(new File(tempFilePath));
        // 2. HLS 비디오로 변환
        ffmpegProcessor.videoHlsMake(path, fileRandomName);

        // 3. 파일명 및 메타데이터 DB 저장 (저장 후 쇼츠 액세스 시 파일명과 cloudfront 주소 전달)

        // 4. S3 파일 업로드 (하나라도 파일 업로드에 실패 시 영상 업로드 프로세스를 멈춰야함.)

        Files
                .walk(Paths.get(path))
                .filter(Files::isRegularFile)
                .forEach((f) -> {
            String name = f.getFileName().toString();
            if (name.endsWith(".ts") || name.endsWith(".m3u8") || name.endsWith(".png"))
                amazonS3.putObject(bucket, fileRandomName + "/" + f.getFileName().toString(), f.toFile());
        });
        // 5. Temp 폴더의 파일 제거
        Files.delete(Paths.get(tempFilePath));

        // 6. 성공 또는 오류 처리
        return fileRandomName;
    }

    // 임시 저장된 쇼츠 영상을 삭제
    public void cancelVideo(String path) throws IOException {

    }
}
