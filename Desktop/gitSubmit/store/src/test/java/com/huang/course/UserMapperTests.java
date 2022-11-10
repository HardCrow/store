package com.huang.course;

import com.huang.course.entity.User;

import com.huang.course.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


//测试类
@SpringBootTest
//测试类不会随同项目打包发送
//RunWith:表示启动这个单元测试(单元测试是不能运行的)，需要传递一个参数，必须是SpringRun实例类型
@RunWith(SpringRunner.class)
public class UserMapperTests {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void insert(){
        User user = new User();

        user.setUsername("huangtest");
        user.setPassword("14545");
//报错可以不用理，sql插入已经成功了
        Integer rows = userMapper.insert(user);
        System.out.println(rows);
    }
    @Test
    public void find(){
     User user=userMapper.findByUsername("huangtest");
        System.out.println(user);
    }
    @Test
    public void updataPasswordByUid(){
     userMapper.updataPasswordByUid(36,"1111","mybatis");
    }
    @Test
    public void findByUid(){
        System.out.println(userMapper.findByUid(36));
    }
    @Test
    public void updateInfoByUid(){
        userMapper.updateInfoByUid(35,"1211111","huang@sd.com",1,"huangtest");
    }
}
