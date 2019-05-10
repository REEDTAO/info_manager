package com.reed.info_manager.mapper;

import com.reed.info_manager.entity.TaskReply;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TaskReplyMapper {
    int insertReply(TaskReply taskReply);
}
