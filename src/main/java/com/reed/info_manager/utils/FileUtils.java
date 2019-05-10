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
    public static boolean saveFileFullPath(MultipartFile file,String fullPath){
        File dest = new File(fullPath);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static boolean saveFileFullPath(MultipartFile file,String fullPath,String userName) {
        File temp = new File(fullPath);
        String[] list = temp.getParentFile().list();
        for (int i=0;i<list.length;i++){
            if(list[i].startsWith(userName)){
                new File(temp.getParent()+"/"+list[i]).delete();
                break;
            }
        }
        return  saveFileFullPath(file,fullPath);
    }



    public static void fileDownload(String filePath, HttpServletResponse response){
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
