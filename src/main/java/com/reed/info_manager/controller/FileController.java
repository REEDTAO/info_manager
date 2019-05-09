package com.reed.info_manager.controller;



import com.reed.info_manager.utils.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/fileManager")
public class FileController {
    @GetMapping("/download/{filePath}")
    public String download (@PathVariable("filePath") String filePath, HttpServletResponse response){
        FileUtils.fileDownload(filePath,response);
        return null;
    }
}
