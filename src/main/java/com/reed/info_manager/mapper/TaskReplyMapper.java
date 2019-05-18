package com.reed.info_manager.mapper;

import com.reed.info_manager.entity.PieChartData;
import com.reed.info_manager.entity.TaskReply;
import com.reed.info_manager.entity.Track;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TaskReplyMapper {
    int insertReply(TaskReply taskReply);

    TaskReply getTaskReplyByTaskId(@Param("taskId") String taskId, @Param("taskResponderId") Integer id);

    int updateTaskReply(TaskReply taskReply);

    List<TaskReply> getTaskReplyListByTaskId(Integer taskId);



    List<PieChartData> getTaskReplyMapByUserId(Integer id);

    List<Track> getTaskReplyByTaskGroupNameAndUserId(@Param("userId") Integer userId, @Param("taskGroupId") Integer taskGroupId);
}
