package com.reed.info_manager.mapper;

import com.reed.info_manager.entity.PieChartData;
import com.reed.info_manager.entity.TaskReply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface TaskReplyMapper {
    int insertReply(TaskReply taskReply);

    TaskReply getTaskReplyByTaskId(@Param("taskId") String taskId, @Param("taskResponderId") Integer id);

    int updateTaskReply(TaskReply taskReply);

    List<TaskReply> getTaskReplyListByTaskId(Integer taskId);



    List<PieChartData> getTaskReplyMapByUserId(Integer id);

    Integer getTaskReplyByTaskGroupNameAndUserId(@Param("userId") Integer userId, @Param("taskGroupId") Integer taskGroupId);
}
