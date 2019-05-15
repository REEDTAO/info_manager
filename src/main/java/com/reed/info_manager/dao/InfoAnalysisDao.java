package com.reed.info_manager.dao;

import com.reed.info_manager.mapper.InfoAnalysisMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class InfoAnalysisDao {
    @Autowired
    InfoAnalysisMapper infoAnalysisMapper;
    public Integer getTaskNumInWeek(Map timeForWeek) {
        return infoAnalysisMapper.getTaskNumInWeek(timeForWeek);

    }
}
