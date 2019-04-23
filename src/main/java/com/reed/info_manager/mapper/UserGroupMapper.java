package com.reed.info_manager.mapper;

import com.reed.info_manager.entity.UserGroup;
import com.reed.info_manager.entity.UserGroupJoin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserGroupMapper {
     int addGroup(@Param("id") Integer id, @Param("groupName") String groupName,@Param("creatorName") String creatorName);

     List<UserGroup> selectAllGroupById(Integer id);

    int deleteGroup(@Param("id") Integer id,@Param("groupName") String groupName);

    List<UserGroupJoin> selectAllGroupByGroupName(String name);
}
