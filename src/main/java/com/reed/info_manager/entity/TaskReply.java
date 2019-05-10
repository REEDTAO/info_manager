package com.reed.info_manager.entity;

import lombok.Data;

import java.util.Date;

@Data
public class TaskReply {
    Integer taskReplyId;
    String taskReplyContent;
    String taskReplyFilePath;
    Integer taskResponderId;
    Integer taskId;
    Date taskReplyTime;
}
