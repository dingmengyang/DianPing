package com.imooc.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 图片保存或更改时，多次用到文件保存，封装成工具类
 */
public class FileUtil {

    public static String save(MultipartFile multipartFile,String savePath) throws IOException {
        String fileName = "" + System.currentTimeMillis() + "_" +multipartFile.getOriginalFilename();
        File file = new File(savePath + fileName);
        File fileFolder = new File(savePath);
        if (!fileFolder.exists()) {
            fileFolder.mkdirs();
            System.out.println(file.getAbsolutePath());
        }

        multipartFile.transferTo(file);
        return fileName;

    }

    public static boolean delete(String path) {
        File file = new File(path);
        return file.exists() && file.delete();
    }
}
