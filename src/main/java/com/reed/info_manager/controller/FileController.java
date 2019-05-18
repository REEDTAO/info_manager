package com.reed.info_manager.controller;



import com.reed.info_manager.constant.Constant;
import com.reed.info_manager.entity.Track;
import com.reed.info_manager.entity.User;
import com.reed.info_manager.service.TaskReplyService;
import com.reed.info_manager.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.reed.info_manager.constant.Constant.FILE_ROOT_DIR;

@Controller
@RequestMapping("/fileManager")
public class FileController {
    @Autowired
    TaskReplyService taskReplyService;
    @Autowired
    HttpSession session;
    @Autowired
    SpringTemplateEngine engine;
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

    @GetMapping("/download/myTrack")
    public String downloadMyTrack(Integer userGroupId,HttpServletResponse response){
        User user = (User) session.getAttribute("user");
        List<Track> list = taskReplyService.getTaskReplyByTaskGroupNameAndUserId(userGroupId, user.getId());
        try {
            createMyTrack(engine,list,user.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileUtils.fileDownload("temp/"+user.getName()+"/pack.zip",response);
        return null;
    }

    public static void createMyTrack(SpringTemplateEngine engine, List<Track> list,String userName) throws IOException {
        Context context = new Context();
        //List<Track> list = taskReplyService.getTaskReplyByTaskGroupNameAndUserId(35,3);
        context.setVariable("list",list);
        String result = engine.process("public/track",context);
        File file = new File(Constant.FILE_ROOT_DIR+"temp/"+userName+"/track.html");
        //System.out.println(Constant.FILE_ROOT_DIR+"temp/2/track1.html");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        if(file.exists()) file.delete();
        file.createNewFile();
        FileOutputStream out = new FileOutputStream(file);
        out.write(result.getBytes());
        out.close();
        List<String> fileList= new ArrayList<>();
        fileList.add("temp/"+userName+"/track.html");
        for (Track track : list){
            fileList.add(track.getFilePath());
        }
        System.out.println(fileList);
        FileUtils.packMyTrack(userName,fileList);
    }

}
