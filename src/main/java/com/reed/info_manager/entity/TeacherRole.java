package com.reed.info_manager.entity;


import lombok.Data;

@Data
public class TeacherRole {
    String teacherRoleId;
    String teacherId;
    String targetRole;
    String roleGroupName;
}
