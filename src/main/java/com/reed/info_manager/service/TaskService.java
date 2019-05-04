package com.reed.info_manager.service;

import com.reed.info_manager.dao.TaskDao;
import com.reed.info_manager.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TaskService {
    @Autowired
    TaskDao taskDao;
    public int addTask(Task task) {
        return taskDao.addTask(task);
    }
}
