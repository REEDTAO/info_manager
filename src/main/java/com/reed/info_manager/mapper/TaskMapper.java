package com.reed.info_manager.mapper;

import com.reed.info_manager.entity.Task;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TaskMapper {
    int insertTask(Task task);

    List<Task> getAllUnfinishedTaskByUserId(Integer id);

    List<Task> getAllFinishedTaskByUserId(Integer id);

    List<Task> getMyReceiveTaskListUnfinished(Integer id);

    List<Task> getMyReceiveTaskListFinished(Integer id);

    Task getTaskByTaskId(Integer taskId);
}
