package com.hw.mixvoice.processor;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;

import java.io.IOException;


public class FFMPEGProcessor {

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
