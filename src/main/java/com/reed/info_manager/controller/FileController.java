package com.reed.info_manager.controller;



import com.reed.info_manager.utils.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/fileManager")
public class FileController {
    @GetMapping("/download")
    public String download (String filePath, HttpServletResponse response){
        FileUtils.fileDownload(filePath,response);
        return null;
    }

    @GetMapping("/download/pack")
    public String downloadPack (String filePath, HttpServletResponse response){
        FileUtils.packFile(filePath.substring(0,filePath.lastIndexOf('/')));
        FileUtils.fileDownload(filePath.substring(0,filePath.lastIndexOf('/'))+"/pack.zip",response);
        return null;
    }
}
