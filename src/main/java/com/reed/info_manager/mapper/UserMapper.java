package com.reed.info_manager.mapper;

import com.reed.info_manager.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    User selectUserByIdAndPassword(@Param("id") String id, @Param("password") String password);

    int addUsers(List<User> list);
}
