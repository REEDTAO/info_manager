package com.reed.info_manager.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface InfoAnalysisMapper {
     Integer getTaskNumInWeek(Map timeForWeek);
}
