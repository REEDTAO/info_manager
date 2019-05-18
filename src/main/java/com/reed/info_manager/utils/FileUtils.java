package com.reed.info_manager.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
        deleteFileByUserName(fullPath,userName);
        return  saveFileFullPath(file,fullPath);
    }

    public static void deleteFileByUserName(String fullPath,String userName){
        File temp = new File(fullPath);
        String[] list = temp.getParentFile().list();
        for (int i=0;i<list.length;i++){
            if(list[i].startsWith(userName)){
                new File(temp.getParent()+"/"+list[i]).delete();
                break;
            }
        }
    }
    public static void deleteFileByFullPath(String filePath){
        File file = new File(filePath);
        if(file.exists())
            file.delete();
    }



    public static void fileDownload(String filePath, HttpServletResponse response){
            File file = new File(FILE_ROOT_DIR + "/" + filePath);
            if (file.exists()) {
                response.setContentType("application/octet-stream");
                response.setHeader("content-type", "application/octet-stream");
                try {
                    response.setHeader("Content-Disposition", "attachment;fileName=" + new String(file.getName().getBytes("UTF-8"),"ISO-8859-1"));// 设置文件名
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
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

    public static void packMyTrack(String userName,List<String> list){
        String zipFilename = FILE_ROOT_DIR+"temp/"+userName+"/pack.zip";
        File[] files = new File[list.size()];
        for(int i=0;i<list.size();i++){
            if (list.get(i).endsWith("/")) continue;
            files[i] = new File(FILE_ROOT_DIR+list.get(i));
        }
        list.set(0,files[0].getName());
        packFile(null,zipFilename,files,list);

    }

    public static void  packFile(String filePath)  {
        packFile(filePath,null,null,null);
    }

    public static void  packFile(String filePath,String zipFileName,File[] fileList,List<String> zipEntryName)  {
        String zipFilename ;
        File[] files ;
        if(filePath==null&&zipFileName!=null&&fileList!=null){
            zipFilename = zipFileName;
            files=fileList;
        }else{
            zipFilename = FILE_ROOT_DIR+filePath+"/pack.zip";
            files = new File(FILE_ROOT_DIR+filePath+"/").listFiles();
        }

        File file = new File(zipFilename);
        ZipOutputStream zipOutputStream=null;
        FileOutputStream fileOutputStream = null;
        if(file.exists()){
            file.delete();
        }
        try {
            file.createNewFile();
            fileOutputStream = new FileOutputStream(file);
            zipOutputStream = new ZipOutputStream(fileOutputStream);
            if(zipEntryName!=null)
                zipFile(files,zipOutputStream,zipEntryName);
            else
                zipFile(file,zipOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                zipOutputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void zipFile(File[] files, ZipOutputStream outputStream) {
        zipFile(files,outputStream,null);
    }

    public static void zipFile(File[] files, ZipOutputStream outputStream,List<String> zipEntryName) {
        int size = files.length;
        for (int i = 0; i < size; i++) {
            File file = files[i];
            zipFile(file, outputStream,zipEntryName.get(i));
        }
    }



    public static void zipFile(File inputFile, ZipOutputStream ouputStream){
        zipFile(inputFile,ouputStream,null);
    }


    public static void zipFile(File inputFile, ZipOutputStream ouputStream,String filePath) {
        if (filePath==null)filePath = inputFile.getName();
        try {
            if (inputFile.exists()) {
                if (inputFile.isFile()) {
                    FileInputStream IN = new FileInputStream(inputFile);
                    BufferedInputStream bins = new BufferedInputStream(IN, 512);
                    ZipEntry entry = new ZipEntry(filePath);
                    ouputStream.putNextEntry(entry);
                    // 向压缩文件中输出数据
                    int nNumber;
                    byte[] buffer = new byte[512];
                    while ((nNumber = bins.read(buffer)) != -1) {
                        ouputStream.write(buffer, 0, nNumber);
                    }
                    // 关闭创建的流对象
                    bins.close();
                    IN.close();
                } else {
                    try {
                        File[] files = inputFile.listFiles();
                        for (int i = 0; i < files.length; i++) {
                            zipFile(files[i], ouputStream);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
