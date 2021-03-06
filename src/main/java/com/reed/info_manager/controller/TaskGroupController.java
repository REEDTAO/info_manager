package com.reed.info_manager.controller;


import com.reed.info_manager.entity.User;
import com.reed.info_manager.entity.UserGroup;
import com.reed.info_manager.entity.UserGroupJoin;
import com.reed.info_manager.entity.UserRole;
import com.reed.info_manager.service.UserGroupService;
import com.reed.info_manager.service.UserRoleService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.reed.info_manager.constant.Constant.FAIL_JOIN_USER_ROLE;
import static com.reed.info_manager.constant.Constant.SUCCESS;

@Controller
@RequestMapping("/task/group")
public class TaskGroupController {

    @Autowired
    HttpSession session;
    @Autowired
    UserGroupService userGroupService;
    @Autowired
    UserRoleService userRoleService;

    final Logger logger = LoggerFactory.getLogger(TaskGroupController.class);


    @GetMapping("/myCreate")
    public String task(Model model){
        User user = (User)session.getAttribute("user");
        List<UserGroup> list = userGroupService.getAllGroup(user.getId());
        model.addAttribute("groupList",list);
        logger.debug(list.toString());
        System.out.println(list);
        return "taskGroup/taskGroupCreate";
    }

    @GetMapping("/add/{groupName}")
    public String addGroup(@PathVariable String groupName, Model model){
        User user = (User) session.getAttribute("user");

        if(userGroupService.addGroup(user.getId(),groupName,user.getName())==1){
            model.addAttribute("message","添加成功！请刷新后查看！");
            return "redirect:/task/group/myCreate";
        }
        return "redirect:/login";

    }

    @GetMapping("/delete/{groupName}")
    public String deleteGroup(@PathVariable String groupName, Model model){
        User user = (User)session.getAttribute("user");
        if(userGroupService.deleteGroup(user.getId(),groupName)==1){
            model.addAttribute("message","删除成功！");
        }
        model.addAttribute("message","删除失败，请联系管理员修改！");
        return "redirect:/task/group/myCreate";
    }

    @GetMapping("/myJoin")
    public String myJoin(Model model, @RequestParam(required = false,value = "message") String message){
        //前端页面修改，添加已加入任务组的查看
        User user = (User) session.getAttribute("user");
        List<UserGroup> list =userRoleService.searchMyJoinUserGroup(user.getId());
        model.addAttribute("list",list);
        if(message!=null)model.addAttribute("message",message);
        return "taskGroup/taskGroupJoin";
    }

    /**
     * 添加任务组：前端使用ajax提起请求返回的String信息通过alert显示
     * @param id
     * @param name
     * @return
     */
    @GetMapping("/Join/{id}/{name}")
    @ResponseBody
    public String join(@PathVariable("id") Integer id,@PathVariable("name") String name){
        User user = (User)session.getAttribute("user");
        UserRole userRole = new UserRole();
        userRole.setUserGroupId(id);
        userRole.setUserGroupName(name);
        userRole.setUserId(user.getId());
        if(userRoleService.joinUserGroup(userRole)==FAIL_JOIN_USER_ROLE){
            return "添加失败，请确认是否重复添加！";
        }else{
            return "添加成功！";
        }

    }


    @GetMapping("/searchGroup/{name}")
    @ResponseBody
    public Map searchGroup(@PathVariable String name){
        List<UserGroupJoin> list = userGroupService.searchGroupByNameForPage(name);
        System.out.println(list);
        HashMap<String ,Object> map = new HashMap<>();
        map.put("data",list);
        return map;
    }

    @GetMapping("/role/delete/{userGroupId}")
    public String deleteRoleByGroupId(@PathVariable Integer userGroupId, Model model, RedirectAttributes attr){
        User user = (User) session.getAttribute("user");

        if(userRoleService.deleteUserRoleByUserGroupId(user.getId(),userGroupId)==SUCCESS){
            attr.addFlashAttribute("message","删除成功！");
        }else{
            attr.addFlashAttribute("message","删除失败，请联系管理员删除！");
        }
        return "redirect:/task/group/myJoin";

    }


//        <tr th:each="user : #{userList}">
//            <td th:text="${user.id}"></td>
//            <td th:text="${user.name}"></td>
//            <td><a href="/task/group/forceDeleteMember/${taskGroupId}/${user.id}">强制退组</a></td>
//        </tr>
//    </tbody>

    @GetMapping("/taskGroupMember/{userGroupId}")
    public String getTaskGroupMember(@PathVariable("userGroupId")Integer userGroupId,Model model){
//        List<User> userList = userRoleService.getUserByUserGroupId(userGroupId);
//        if (userList!=null){
//            model.addAttribute("userList",userList);
//            model.addAttribute("userGroupId",userGroupId);
//        }
        model.addAttribute("userGroupId",userGroupId);
        return "taskGroup/taskGroupMember";
    }
    @GetMapping("/taskGroupMember/getMember/{userGroupId}")
    @ResponseBody
    public Map getMember(@PathVariable("userGroupId")Integer userGroupId,Model model){
        List<User> userList = userRoleService.getUserByUserGroupId(userGroupId);
        if (userList!=null){
            model.addAttribute("userList",userList);
            model.addAttribute("userGroupId",userGroupId);
        }
        Map<String,List> map = new HashMap<>();
        map.put("data",userList);
        return map;
    }
    @GetMapping("/forceDeleteMember/{userGroupId}/{userId}")
    public String forceDeleteMember(@PathVariable("userGroupId")Integer userGroupId,@PathVariable("userId")Integer userId, Model model){
        if (userRoleService.deleteUserRoleByUserGroupIdAndUserId(userGroupId,userId)==1){
            model.addAttribute("message","操作成功");
        }
        return getTaskGroupMember(userGroupId,model);
    }
}
