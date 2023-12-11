package com.hw.mixvoice.processor;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FFMPEGProcessor ffmpegProcessor = new FFMPEGProcessor();
//        ffmpegProcessor.encode();
        ffmpegProcessor.videoHlsMake();
        System.out.println("Hello world!");
    }
}