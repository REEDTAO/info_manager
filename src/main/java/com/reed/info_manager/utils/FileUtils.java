package com.reed.info_manager.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

import static com.reed.info_manager.constant.Constant.FILE_ROOT_DIR;

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

    public static void fileDownload(String filePath, HttpServletResponse response){
            //设置文件路径
            String realPath = "D:\\eclipsworksapce1\\upgrade\\src\\main\\webapp\\upload\\tbox\\456789\\";
            File file = new File(FILE_ROOT_DIR + "/" + filePath);
            if (file.exists()) {
                response.setContentType("application/octet-stream");//
                response.setHeader("content-type", "application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;fileName=" + file.getName());// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("success");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    }


}
