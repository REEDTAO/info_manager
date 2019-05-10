package com.reed.info_manager.dao;

import com.reed.info_manager.entity.TaskReply;
import com.reed.info_manager.mapper.TaskReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class TaskReplyDao {
    @Autowired
    TaskReplyMapper taskReplyMapper;
    public int addReply(TaskReply taskReply) {
        return  taskReplyMapper.insertReply(taskReply);
    }

    public TaskReply getTaskReplyByTaskId(String taskId, Integer id) {
        return taskReplyMapper.getTaskReplyByTaskId(taskId,id);
    }

    public int updateTaskReply(TaskReply taskReply) {
        return taskReplyMapper.updateTaskReply(taskReply);
    }
}
