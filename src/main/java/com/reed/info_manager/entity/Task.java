package com.reed.info_manager.entity;


import lombok.Data;


import java.util.Date;
import java.util.List;

@Data
public class Task {
    Integer taskId;
    Integer taskCreatorId;
    Date taskCreateTime;
    Date taskEndTime;
    String taskContent;
    Integer taskSubmittedNum;
    Integer taskTargetNum;
    String taskFilePath;
    String taskTitle;
    Integer taskFinished;
    List<Integer> taskTargetGroupIds;
    List<String> taskTargetGroupList;

}
