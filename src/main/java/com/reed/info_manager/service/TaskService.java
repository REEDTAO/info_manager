package com.reed.info_manager.service;

import com.reed.info_manager.dao.TaskDao;
import com.reed.info_manager.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskService {
    @Autowired
    TaskDao taskDao;
    public int addTask(Task task) {
        return taskDao.addTask(task);
    }

    public List<Task> getAllUnfinishedTaskByUserId(Integer id) {
        return  taskDao.getAllUnfinishedTaskByUserId(id);
    }

    public List<Task> getAllFinishedTaskByUserId(Integer id) {
        return  taskDao.getAllFinishedTaskByUserId(id);
    }

    public List<Task> getMyReceiveTaskListUnfinished(Integer id) {
        return taskDao.getMyReceiveTaskListUnfinished(id);
    }

    public List<Task> getMyReceiveTaskListFinished(Integer id) {
        return  taskDao.getMyReceiveTaskListFinished(id);
    }
}
