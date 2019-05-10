package com.reed.info_manager.mapper;

import com.reed.info_manager.entity.TaskReply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TaskReplyMapper {
    int insertReply(TaskReply taskReply);

    TaskReply getTaskReplyByTaskId(@Param("taskId") String taskId, @Param("taskResponderId") Integer id);

    int updateTaskReply(TaskReply taskReply);
}
