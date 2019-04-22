package com.reed.info_manager.mapper;

import com.reed.info_manager.entity.UserGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserGroupMapper {
     int addGroup(@Param("id") Integer id, @Param("groupName") String groupName);

     List<UserGroup> selectAllGroupById(Integer id);

    int deleteGroup(@Param("id") Integer id,@Param("groupName") String groupName);
}
