package com.huang.course.service;


import com.huang.course.entity.User;

import javax.xml.crypto.Data;

public interface UserService {
    //@pram user用户的数据对象
    void reg(User user);
    User login(String username,String password);
    void changPassword(Integer uid,String username,String oldPassword,String newPassword);
    User getByUid(Integer uid);
    void changInfo(Integer uid,String username,User user);

}
