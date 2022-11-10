package com.huang.course.mapper;


import com.huang.course.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    Integer insert(User user);
    User findByUsername(String username);
    //mybatis 在mapper.xml 里查询数据库数据时，不支持传多个参数查询因此要加@Param注解
    Integer updataPasswordByUid(@Param("uid")Integer uid, @Param("password")String password,@Param("modifiedUser") String modifiedUser);
    User findByUid(Integer uid);
    Integer updateInfoByUid(@Param("uid")Integer uid,@Param("phone") String phone,@Param("email")String email,@Param("gender")Integer gender,@Param("modifiedUser")String modifiedUser);
}
