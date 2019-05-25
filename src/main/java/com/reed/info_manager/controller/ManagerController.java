package com.reed.info_manager.controller;

import com.reed.info_manager.entity.Manager;
import com.reed.info_manager.entity.Parent;
import com.reed.info_manager.entity.User;
import com.reed.info_manager.service.ParentService;
import com.reed.info_manager.service.UserService;
import com.reed.info_manager.utils.ExcelUtils;
import com.reed.info_manager.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.reed.info_manager.constant.Constant.FILE_ROOT_DIR;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    UserService userService;
    @Autowired
    HttpSession session;
    @Autowired
    ParentService parentService;

    @GetMapping("/index")
    public String managerIndex(){
        return "manager/userInfoManage";
    }

    @GetMapping("/{role}/searchName/{name}")
    @ResponseBody
    public Map  searchName(@PathVariable("name") String name,@PathVariable("role") String role){
        Map<String,Object> map = new HashMap<>();
        List list=null;
        if(role.equals("user"))
            list = userService.searchUserByName(name);
        else
            list=parentService.searchUserByName(name);
        map.put("data",list);
        return map;
    }
    @GetMapping("/{role}/searchId/{id}")
    @ResponseBody
    public Map  searcId(@PathVariable("id") Integer id,@PathVariable("role")String role){
        Map<String,Object> map = new HashMap<>();
        List list=null;
        if(role.equals("user"))
            list = userService.searchUserById(id);
        else
            list = parentService.searchParentById(id);
        map.put("data",list);
        return map;
    }
    @PostMapping("/{role}/update")
    public String updateUser(Parent parent, Model model,@PathVariable("role")String role){
        Integer num = 0;
        if(role.equals("user"))
            num=userService.updateUserByUserId(parent);
        else
            num=parentService.updateParentByUserId(parent);
        if(num==1){
            model.addAttribute("message","修改成功！");
        }else{
            model.addAttribute("message","修改失败！");
        }
        if(role.equals("user"))
            return managerIndex();
        else
            return parentInfoManage();
    }
    @GetMapping("/{role}/delete/{userId}")
    public String deleteUser(@PathVariable("userId")Integer userId,Model model,@PathVariable("role")String role){
        Integer num = 0;
        if(role.equals("user"))
            num=userService.deleteUserByUserId(userId);
        else
            num=parentService.deleteParentByUserId(userId);
        if(num==1){
            model.addAttribute("message","删除成功！");
        }else{
            model.addAttribute("message","删除失败！");
        }
        if(role.equals("user"))
            return managerIndex();
        else
            return parentInfoManage();
    }
    @PostMapping("/{role}/excelImport")
    public String userImportByExcel(MultipartFile file, Model model,@PathVariable("role")String role) {
        String filePath = FILE_ROOT_DIR + "manager/temp/" + new Date().getTime();
        if (!file.isEmpty()) {
            if (!FileUtils.saveFile(file, filePath)) {
                model.addAttribute("message", "文件保存失败！");
            } else {
                try {
                    Integer num = 0;
                    List<User> list = ExcelUtils.parseFileToUserList(filePath+"/"+file.getOriginalFilename());
                    if (role.equals("user"))
                        num=userService.addUsers(list);
                    else
                        num=parentService.addParents(list);
                    if (num>=1){
                        model.addAttribute("message","导入成功！");
                    }else model.addAttribute("message","导入失败！请检查文件格式！");
                } catch (IOException e) {
                    e.printStackTrace();
                    model.addAttribute("message","导入失败！请检查文件格式！");
                }
            }
        }
        File temp = new File(filePath);
        if(temp.exists()) {
            temp.delete();
            temp.getParentFile().delete();
        }
        if(role.equals("user"))
            return managerIndex();
        else
            return parentInfoManage();
    }

    @GetMapping("/userInfoManage")
    public String userInfoManage(){
        return managerIndex();
    }

    @GetMapping("/parentInfoManage")
    public String parentInfoManage(){
        return "manager/parentInfoManage";
    }


}
