package com.reed.info_manager.controller;

import com.reed.info_manager.entity.Manager;
import com.reed.info_manager.entity.User;
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

    @GetMapping("/index")
    public String managerIndex(){
        return "manager/userInfoManage";
    }

    @GetMapping("/user/searchName/{name}")
    @ResponseBody
    public Map  searchName(@PathVariable("name") String name){
        Map<String,Object> map = new HashMap<>();
        List<User> list = userService.searchUserByName(name);
        map.put("data",list);
        return map;
    }
    @GetMapping("/user/searchId/{id}")
    @ResponseBody
    public Map  searcId(@PathVariable("id") Integer id){
        Map<String,Object> map = new HashMap<>();
        List<User> list = userService.searchUserById(id);
        map.put("data",list);
        return map;
    }
    @PostMapping("/user/update")
    public String updateUser(User user, Model model){
        if(userService.updateUserByUserId(user)==1){
            model.addAttribute("message","修改成功！");
        }else{
            model.addAttribute("message","修改失败！");
        }
        return managerIndex();
    }
    @GetMapping("/user/delete/{userId}")
    public String deleteUser(@PathVariable("userId")Integer userId,Model model){
        if(userService.deleteUserByUserId(userId)==1){
            model.addAttribute("message","删除成功！");
        }else{
            model.addAttribute("message","删除失败！");
        }
        return managerIndex();
    }
    @PostMapping("/user/excelImport")
    public String userImportByExcel(MultipartFile file, Model model) {
        Manager manager = (Manager) session.getAttribute("manager");
        String filePath = FILE_ROOT_DIR + "manager/temp/" + new Date().getTime();
        if (!file.isEmpty()) {
            if (!FileUtils.saveFile(file, filePath)) {
                model.addAttribute("message", "文件保存失败！");
            } else {
                try {
                    List<User> list = ExcelUtils.parseFileToUserList(filePath+"/"+file.getOriginalFilename());
                    if (userService.addUsers(list)>=1){
                        model.addAttribute("message","导入成功！");
                    }else model.addAttribute("message","导入失败！请检查文件格式！");
                } catch (IOException e) {
                    e.printStackTrace();
                    model.addAttribute("message","导入失败！请检查文件格式！");
                }
            }
        }
        File temp = new File(filePath);
        if(temp.exists()) temp.delete();
        return managerIndex();
    }


}
