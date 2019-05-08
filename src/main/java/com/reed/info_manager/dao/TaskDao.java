package com.reed.info_manager.dao;

import com.reed.info_manager.entity.Task;
import com.reed.info_manager.mapper.TaskMapper;

import com.reed.info_manager.mapper.TaskTargetMapper;
import com.reed.info_manager.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskDao {
    @Autowired
    TaskMapper taskMapper;
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    TaskTargetMapper taskTargetMapper;


    public int addTask(Task task) {
        Integer targetNum = userRoleMapper.selectUserNumByGroupName(task.getTaskTargetGroupIds());
        task.setTaskTargetNum(targetNum);
        if (taskMapper.insertTask(task)!=1)return 0;
        return taskTargetMapper.insertTaskTarget(task.getTaskId(),task.getTaskTargetGroupIds());

    }

    public List<Task> getAllUnfinishedTaskByUserId(Integer id) {
        return  taskMapper.getAllUnfinishedTaskByUserId(id);
    }
}
