package com.hw.mixvoice.processor;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;

import java.io.IOException;


public class FFMPEGProcessor {
    public static final String UPLOAD_DIR = "\\video";
    public static final String TS_PATH = "\\ts";
    public void videoHlsMake() throws IOException {
        String filePath = "D:\\GitRepository_HW\\web\\mix-voice\\backend\\server\\src\\main\\resources\\static\\";
        String fileName = "KakaoTalk_20230301_205552507";
        String fileExtension = ".mp4";
        FFmpeg ffmpeg = new FFmpeg("D:\\libaraies\\ffmpeg-6.1-full_build\\bin\\ffmpeg");
        FFprobe ffprobe = new FFprobe("D:\\libaraies\\ffmpeg-6.1-full_build\\bin\\ffprobe");

        FFmpegBuilder builder = new FFmpegBuilder()
                //.overrideOutputFiles(true) // 오버라이드 여부
                .setInput(filePath + fileName + fileExtension) // 동영상파일
                .addOutput(filePath + TS_PATH + "\\" + fileName + ".m3u8") // 썸네일 경로
                .addExtraArgs("-profile:v", "baseline") //
                .addExtraArgs("-level", "3.0") //
                .addExtraArgs("-start_number", "0") //
                .addExtraArgs("-hls_time", "10") //
                .addExtraArgs("-hls_list_size", "0") //
                .addExtraArgs("-f", "hls") //
                .done();
        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);
        executor.createJob(builder).run();

        // 이미지 파일 생성
        FFmpegBuilder builderThumbNail = new FFmpegBuilder()
                .overrideOutputFiles(true) // 오버라이드 여부
                .setInput(filePath + fileName + fileExtension) // 동영상파일
                .addExtraArgs("-ss", "00:00:03") // 썸네일 추출 시작점
                .addOutput(filePath + UPLOAD_DIR + "\\" + fileName + ".png") // 썸네일 경로
                .setFrames(1) // 프레임 수
                .done();
        FFmpegExecutor executorThumbNail = new FFmpegExecutor(ffmpeg, ffprobe);
        executorThumbNail.createJob(builderThumbNail).run();

    }
    public void encode() throws IOException {
        FFmpeg ffmpeg = new FFmpeg("D:\\libaraies\\ffmpeg-6.1-full_build\\bin\\ffmpeg");
        FFprobe ffprobe = new FFprobe("D:\\libaraies\\ffmpeg-6.1-full_build\\bin\\ffprobe");

        FFmpegBuilder builder = new FFmpegBuilder()
            .setInput("D:\\GitRepository_HW\\web\\mix-voice\\backend\\server\\src\\main\\resources\\static\\offspring.wav")     // Filename, or a FFmpegProbeResult
            .overrideOutputFiles(true) // Override the output if it exists
            .addOutput("output.mp4")   // Filename for the destination
            .setFormat("mp4")        // Format is inferred from filename, or can be set
            .disableSubtitle()       // No subtiles
            .setAudioChannels(1)         // Mono audio
            .setAudioCodec("aac")        // using the aac codec
            .setVideoCodec("libx264")     // Video using x264
            .setVideoResolution(640, 480) // at 640x480 resolution
            .setStrict(FFmpegBuilder.Strict.EXPERIMENTAL).done(); // Allow FFmpeg to use experimental specs

        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);

        executor.createJob(builder).run();
//        executor.createTwoPassJob(builder).run();
    }
}
