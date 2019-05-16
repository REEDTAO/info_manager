package com.reed.info_manager.dao;

import com.reed.info_manager.entity.PieChartData;
import com.reed.info_manager.entity.Task;
import com.reed.info_manager.entity.TaskReply;
import com.reed.info_manager.mapper.TaskMapper;
import com.reed.info_manager.mapper.TaskReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class TaskReplyDao {
    @Autowired
    TaskReplyMapper taskReplyMapper;
    @Autowired
    TaskMapper taskMapper;
    public int addReply(TaskReply taskReply) {
        int num = taskMapper.addReplyNum(taskReply.getTaskId());
        return  taskReplyMapper.insertReply(taskReply);
    }

    public TaskReply getTaskReplyByTaskId(String taskId, Integer id) {
        return taskReplyMapper.getTaskReplyByTaskId(taskId,id);
    }

    public int updateTaskReply(TaskReply taskReply) {
        return taskReplyMapper.updateTaskReply(taskReply);
    }

    public List<TaskReply> getTaskReplyListByTaskId(Integer taskId) {
        return taskReplyMapper.getTaskReplyListByTaskId(taskId);
    }

    /**
     * 通过userId
     *      获取每个组的name
     *      获取每个组的value
     *
     * @param id
     * @return
     */
    public List<PieChartData> getTaskReplyListByUserId(Integer id) {

        return taskReplyMapper.getTaskReplyMapByUserId(id);

    }

    public Integer getTaskReplyByTaskGroupNameAndUserId(Integer userId, Integer taskGroupId) {
        return  taskReplyMapper.getTaskReplyByTaskGroupNameAndUserId(userId,taskGroupId);
    }
}
