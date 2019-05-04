package com.reed.info_manager.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TaskTargetMapper {

    Integer insertTaskTarget(@Param("taskId") Integer taskId, @Param("list") List<Integer> taskTargetGroupIds);
}
