package com.reed.info_manager.mapper;

import com.reed.info_manager.entity.Manager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ManagerMapper {
    Manager loginMapper(@Param("id") Integer id,@Param("password") String password);
}
