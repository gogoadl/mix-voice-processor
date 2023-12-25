package com.hw.mixvoice.util;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import net.bramp.ffmpeg.builder.FFmpegOutputBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@Component
public class FFMPEGProcessor {
    FFmpeg ffmpeg;
    FFprobe ffprobe;
    public FFMPEGProcessor(
            @Value("${video.path.ffmpeg}") String ffmpegPath,
            @Value("${video.path.ffprobe}") String ffprobePath
    ) throws IOException {
        ffmpeg = new FFmpeg(ffmpegPath);
        ffprobe = new FFprobe(ffprobePath);
    }

    public void videoHlsMake(String filePath, String fileName) throws IOException {
        String thumbnailPath = filePath + "thumbnail.png";

        FFmpegBuilder builder = new FFmpegBuilder()
                //.overrideOutputFiles(true) // 오버라이드 여부
                .setInput(filePath+fileName) // 동영상파일
                .addOutput(filePath+"/%vplaylist.m3u8")
//                .addExtraArgs("-profile:v", "baseline") //
//                .addExtraArgs("-level", "3.0") //
                .addExtraArgs("-start_number", "0") //
                .addExtraArgs("-hls_time", "10") //
                .addExtraArgs("-hls_list_size", "0") //
                .addExtraArgs("-f", "hls") //
                .addExtraArgs("-hls_segment_filename", filePath + "/%voutput_%03d.ts") // ts 파일 이름 (ex: output_000.ts)
                .addExtraArgs("-master_pl_name", "master.m3u8") // 마스터 재생 파일

                .addExtraArgs("-map", "0:v")
                .addExtraArgs("-map", "0:v")
                .addExtraArgs("-map", "0:v")
                .addExtraArgs("-var_stream_map", "v:0,name:1080 v:1,name:720 v:2,name:480") // 출력 매핑

                // 1080 화질 옵션
                .addExtraArgs("-b:v:0", "5000k")
                .addExtraArgs("-maxrate:v:0", "5000k")
                .addExtraArgs("-bufsize:v:0", "10000k")
                .addExtraArgs("-s:v:0", "1080x1920")
                .addExtraArgs("-crf:v:0", "15")
                .addExtraArgs("-b:a:0", "128k")

                // 720 화질 옵션
                .addExtraArgs("-b:v:1", "2500k")
                .addExtraArgs("-maxrate:v:1", "2500k")
                .addExtraArgs("-bufsize:v:1", "5000k")
                .addExtraArgs("-s:v:1", "720x1280")
                .addExtraArgs("-crf:v:1", "22")
                .addExtraArgs("-b:a:1", "96k")

                // 480 화질 옵션
                .addExtraArgs("-b:v:2", "1000k")
                .addExtraArgs("-maxrate:v:2", "1000k")
                .addExtraArgs("-bufsize:v:2", "2000k")
                .addExtraArgs("-s:v:2", "480x854")
                .addExtraArgs("-crf:v:2", "28")
                .addExtraArgs("-b:a:2", "64k")
                .done();
        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);
        executor.createJob(builder).run();

        FFmpegBuilder builderThumbNail = new FFmpegBuilder()
                .overrideOutputFiles(true) // 오버라이드 여부
                .setInput(filePath+fileName) // 동영상파일
                .addExtraArgs("-ss", "00:00:03") // 썸네일 추출 시작점
                .addOutput(thumbnailPath) // 썸네일 경로
                .setFrames(1) // 프레임 수
                .done();
        FFmpegExecutor executorThumbNail = new FFmpegExecutor(ffmpeg, ffprobe);
        executorThumbNail.createJob(builderThumbNail).run();

    }
}
