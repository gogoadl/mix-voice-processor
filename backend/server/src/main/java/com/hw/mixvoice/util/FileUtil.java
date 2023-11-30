package com.hw.mixvoice.util;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;

import java.io.*;
import java.nio.file.Files;

import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileUtil {
    public static MultipartFile getMultipartFile() throws IOException {
        File file = new File(new File("").getAbsolutePath() + "/src/main/resources/static/images/jpa.png");
        FileItem fileItem = new DiskFileItem("originFile", Files.probeContentType(file.toPath()), false, file.getName(), (int) file.length(), file.getParentFile());

        try {
            InputStream input = new FileInputStream(file);
            OutputStream os = fileItem.getOutputStream();
            IOUtils.copy(input, os);
            // Or faster..
            // IOUtils.copy(new FileInputStream(file), fileItem.getOutputStream());
        } catch (IOException ex) {
            // do something.
        }

        //jpa.png -> multipart 변환
        MultipartFile mFile = new CommonsMultipartFile(fileItem);
        return mFile;
    }
    public static MultipartFile getMultipartFile(String path) throws IOException {
        File file = new File(path);
        FileItem fileItem = new DiskFileItem("originFile", Files.probeContentType(file.toPath()), false, file.getName(), (int) file.length(), file.getParentFile());

        try {
            InputStream input = new FileInputStream(file);
            OutputStream os = fileItem.getOutputStream();
            IOUtils.copy(input, os);
            // Or faster..
            // IOUtils.copy(new FileInputStream(file), fileItem.getOutputStream());
        } catch (IOException ex) {
            // do something.
        }

        //jpa.png -> multipart 변환
        MultipartFile mFile = new CommonsMultipartFile(fileItem);
        return mFile;
    }
}
