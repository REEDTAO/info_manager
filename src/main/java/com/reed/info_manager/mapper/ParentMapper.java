package com.reed.info_manager.mapper;

import com.reed.info_manager.entity.Parent;
import com.reed.info_manager.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ParentMapper {
    List<Parent> getParentByUserId(Integer id);

    int removeParentByUserId(@Param("id") Integer id,@Param("userId") Integer userId);

    Parent loginParent(@Param("id") Integer id,@Param("password") String password);

    int updateUserByUserId(Parent parent);

    int removeStudentByUserId(Integer userId, Integer id);

    int bindStudent(@Param("parentId") Integer parentId, @Param("userId") Integer userId);

    List<Parent> searchUserByName(String name);

    List<Parent> searchParentById(Integer id);

    Integer updateParentByUserId(Parent parent);

    Integer deleteParentByUserId(Integer parentId);

    Integer addParents(List<User> list);

    int registerParent(Parent parent);
}
