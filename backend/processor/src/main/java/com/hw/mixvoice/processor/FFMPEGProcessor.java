package com.hw.mixvoice.processor;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;

import java.io.IOException;

public class FFMPEGProcessor {

    public void encode() throws IOException {
        FFmpeg ffmpeg = new FFmpeg("/path/to/ffmpeg");
        FFprobe ffprobe = new FFprobe("/path/to/ffprobe");

        FFmpegBuilder builder = new FFmpegBuilder()
            .setInput("Nothing.wav")     // Filename, or a FFmpegProbeResult
            .overrideOutputFiles(true) // Override the output if it exists
            .addOutput("output.mp4")   // Filename for the destination
            .setFormat("mp4")        // Format is inferred from filename, or can be set
            .setTargetSize(250_000)  // Aim for a 250KB file
            .disableSubtitle()       // No subtiles
            .setAudioChannels(1)         // Mono audio
            .setAudioCodec("aac")        // using the aac codec
            .setVideoCodec("libx264")     // Video using x264
            .setVideoResolution(640, 480) // at 640x480 resolution
            .setStrict(FFmpegBuilder.Strict.EXPERIMENTAL).done(); // Allow FFmpeg to use experimental specs

        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);
// Run a one-pass encode
        executor.createJob(builder).run();
// Or run a two-pass encode (which is better quality at the cost of being slower)
        executor.createTwoPassJob(builder).run();
    }
}
