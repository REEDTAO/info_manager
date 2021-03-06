package com.reed.info_manager.service;

import com.reed.info_manager.dao.TaskReplyDao;
import com.reed.info_manager.entity.PieChartData;
import com.reed.info_manager.entity.TaskReply;
import com.reed.info_manager.entity.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<TaskReply> getTaskReplyListByTaskId(Integer taskId) {
        return taskReplyDao.getTaskReplyListByTaskId(taskId);
    }

    public List<PieChartData> getTaskReplyListByUserId(Integer id) {
        return taskReplyDao.getTaskReplyListByUserId(id);
    }

    public List<Track> getTaskReplyByTaskGroupNameAndUserId(Integer taskGroupId, Integer userId ) {

        return taskReplyDao.getTaskReplyByTaskGroupNameAndUserId(taskGroupId,userId);
    }
}
