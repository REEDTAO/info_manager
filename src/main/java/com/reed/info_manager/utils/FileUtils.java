package com.reed.info_manager.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUtils {
    public static boolean saveFile(MultipartFile file,String filePath){
        File dest = new File(filePath+"/"+file.getOriginalFilename());
        if (!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }


}
