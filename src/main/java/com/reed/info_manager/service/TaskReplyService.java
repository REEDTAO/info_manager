package com.reed.info_manager.service;

import com.reed.info_manager.dao.TaskReplyDao;
import com.reed.info_manager.entity.TaskReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class TaskReplyService {
    @Autowired
    TaskReplyDao taskReplyDao;
    public int addReply(TaskReply taskReply) {
        return  taskReplyDao.addReply(taskReply);
    }

    public TaskReply getTaskReplyByTaskId(String taskId, Integer id) {
        return  taskReplyDao.getTaskReplyByTaskId(taskId,id);
    }

    public int updateTaskReply(TaskReply taskReply) {
        return taskReplyDao.updateTaskReply(taskReply);
    }
}
