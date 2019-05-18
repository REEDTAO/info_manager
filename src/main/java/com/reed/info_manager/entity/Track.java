package com.reed.info_manager.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Track {
    String taskTitle;
    String content;
    String filePath;
    Date replyTime;

}
