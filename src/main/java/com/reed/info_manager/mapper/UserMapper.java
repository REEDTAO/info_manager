package com.reed.info_manager.mapper;

import com.reed.info_manager.entity.User;
import com.reed.info_manager.entity.UserInfoDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
     String getUserNameByUserId(Integer taskCreatorId) ;

    User selectUserByIdAndPassword(@Param("id") String id, @Param("password") String password);

    int addUsers(List<User> list);

    UserInfoDetail getUserDetailByUserId(Integer userId);

    int updateUserByUserId(User user);

    List<User> getUserByParentId(Integer id);

    int findUserByIdAndName(@Param("id") Integer id,@Param("name") String name);

    List<User> searchUserByName(String name);

    List<User> searchUserById(Integer id);

    int deleteUserByUserId(Integer userId);
}
