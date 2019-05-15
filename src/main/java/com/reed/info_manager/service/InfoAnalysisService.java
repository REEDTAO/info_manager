package com.reed.info_manager.service;

import com.reed.info_manager.dao.InfoAnalysisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Map;

@Service
public class InfoAnalysisService {
    @Autowired
    InfoAnalysisDao infoAnalysisDao;
    public Integer getTaskNumInWeek(Map timeForWeek) {
        return infoAnalysisDao.getTaskNumInWeek(timeForWeek);
    }
}
