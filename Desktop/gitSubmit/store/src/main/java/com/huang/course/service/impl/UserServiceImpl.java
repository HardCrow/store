package com.huang.course.service.impl;


import com.huang.course.entity.User;
import com.huang.course.mapper.UserMapper;
import com.huang.course.service.UserService;
import com.huang.course.service.ex.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

        @Autowired
        private  UserMapper userMapper;
        @Override
        public  void reg(User user){
            String username= user.getUsername();
            User result = userMapper.findByUsername(username);
            if(result!=null ){
                throw new UsernameDuplicatedException("用户名被占用");
            }
            user.setIsDelete(0);
            user.setCreatedUser(user.getUsername());
            user.setModifiedUser(user.getUsername());


            //密码加密处理的实现:MD5算法的形式：乱七八糟的字符
        //（串加password加串）-----md5进行加密，连续加载三次
        //盐值+password+盐值---随机的串
        String oldPassword =user.getPassword();
        //随机生成一个串
        String salt = UUID.randomUUID().toString().toUpperCase();
        //补全数据：盐值的记录
        user.setSalt(salt);
        //将密码和盐值作为一个整体进行加密处理
        String md5Password = getMD5Password(oldPassword, salt);
        //将加密之后的密码设置到user对象中
        user.setPassword(md5Password);
        //执行注册业务功能的实现
        Integer row =userMapper.insert(user);
        if(row!=1){
               throw new InsertException("在用户注册的过程中产生了未知的异常");
        }

    }
    @Override
    public User login(String username, String password) {
        User result = userMapper.findByUsername(username);
        if(result==null){
           throw new UsernameNotFoundException("用户数据不存在");
        }
        String oldPassword = result.getPassword();
        //插入后就已经加密了3次 取出不需要在进行加密
        String salt= result.getSalt();
        String newMd5Password=getMD5Password(password,salt);
        //这里就是生成与之对应的密码，getMD5Password方法就是加密三次
        if(!newMd5Password.equals(oldPassword)){
            throw new PasswordNotMatchException("用户密码不正确");
        }
        if (result.getIsDelete()==1){
            throw new UsernameDuplicatedException("用户数据不存在");
        }
        User user = new User();
       user.setAvatar(result.getAvatar());
       user.setUid(result.getUid());
       user.setUsername(result.getUsername());
        return user;
    }

    @Override
    public void changPassword(Integer uid, String username, String oldPassword, String newPassword) {
        User result = userMapper.findByUid(uid);
        if (result== null || result.getIsDelete()==1){
                throw   new UsernameNotFoundException("用户数据不存在");
        }

        String oldMd5Password = getMD5Password(oldPassword, result.getSalt());
        if (!result.getPassword().equals(oldMd5Password)){
            throw new PasswordNotMatchException("密码错误");
        }
        String newMd5Password=getMD5Password(newPassword,result.getSalt());
        Integer rows = userMapper.updataPasswordByUid(uid, newMd5Password, username);
        if (rows!=1){
            throw new UpdateException("更新数据产生未知的异常");
        }
    }

    @Override
    public User getByUid(Integer uid) {
        User result = userMapper.findByUid(uid);
        if (result== null || result.getIsDelete()==1){
              throw new UsernameNotFoundException("用户数据不存在");
        }
        User user = new User();
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        user.setEmail(result.getEmail());
        user.setGender(result.getGender());
        return user;
    }

    @Override
    public void changInfo(Integer uid, String username, User user) {
        User result = userMapper.findByUid(uid);
        if (result== null || result.getIsDelete()==1){
            throw new UsernameNotFoundException("用户数据不存在");
        }
        user.setUid(uid);
        user.setUsername(username);
        user.setModifiedUser(username);
        Integer rows =userMapper.updateInfoByUid(uid,user.getPhone(),user.getEmail(),user.getGender(),user.getModifiedUser());
         if (rows !=1){
             throw new UpdateException("更新数据时产生未知异常");
         }

    }

    //定一个md5算法加密处理

    private String getMD5Password(String password,String salt){
    //md5加密算法的调用(三次加密)
        for(int i=0;i<3;i++){
           password= DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }
        return password;
    }
}

