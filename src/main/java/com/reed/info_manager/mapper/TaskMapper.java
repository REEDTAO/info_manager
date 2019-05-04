package com.reed.info_manager.mapper;

import com.reed.info_manager.entity.Task;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TaskMapper {
    int insertTask(Task task);
}
