package com.reed.info_manager.dao;

import com.reed.info_manager.entity.Task;
import com.reed.info_manager.entity.UserGroup;
import com.reed.info_manager.mapper.TaskMapper;

import com.reed.info_manager.mapper.TaskTargetMapper;
import com.reed.info_manager.mapper.UserGroupMapper;
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
    @Autowired
    UserGroupMapper userGroupMapper;


    public int addTask(Task task) {
        Integer targetNum = userRoleMapper.selectUserNumByGroupName(task.getTaskTargetGroupIds());
        task.setTaskTargetNum(targetNum);
        if (taskMapper.insertTask(task)!=1)return 0;
        return taskTargetMapper.insertTaskTarget(task.getTaskId(),task.getTaskTargetGroupIds());

    }

    public List<Task> getAllUnfinishedTaskByUserId(Integer id) {
        List<Task> taskList = taskMapper.getAllUnfinishedTaskByUserId(id);
        if(taskList!=null){
            for (Task task: taskList) {
                task.setTaskTargetGroupList(taskTargetMapper.getTargetGroupList(task.getTaskId()));
            }
        }

        return  taskList;
    }

    public List<Task> getAllFinishedTaskByUserId(Integer id) {
        List<Task> taskList = taskMapper.getAllFinishedTaskByUserId(id);
        if(taskList!=null){
            for (Task task: taskList) {
                task.setTaskTargetGroupList(taskTargetMapper.getTargetGroupList(task.getTaskId()));
            }
        }
        return  taskMapper.getAllFinishedTaskByUserId(id);
    }

    public List<Task> getMyReceiveTaskListUnfinished(Integer id) {
        return taskMapper.getMyReceiveTaskListUnfinished(id);
    }

    public List<Task> getMyReceiveTaskListFinished(Integer id) {
        return taskMapper.getMyReceiveTaskListFinished(id);
    }

    public Task getTaskByTaskId(Integer taskId) {
        return  taskMapper.getTaskByTaskId(taskId);
    }

    public int deleteTaskByTaskId(Integer taskId) {
        return taskMapper.deleteTaskByTaskId(taskId);
    }

    public int updateTaskByTaskId(Task task) {
        return taskMapper.updateTaskByTaskId(task);
    }

    public int finishTask(Integer taskId) {
        return taskMapper.finishTask(taskId);
    }
}
