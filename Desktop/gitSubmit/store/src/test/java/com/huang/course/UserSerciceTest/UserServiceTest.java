package com.huang.course.UserSerciceTest;
import com.huang.course.entity.User;


import com.huang.course.service.UserService;
import com.huang.course.service.ex.ServiceException;
import netscape.security.UserTarget;
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
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Test
    public void reg(){
        try {
            User users = new User();
            users.setUsername("huang6");
            users.setPassword("123");
            userService.reg(users);
            //要测试这个地方 先把userserviceimpl的插入删掉 不然会重复，然后报错
            System.out.println("okkkkkkkkkkkkkkk");
        } catch (ServiceException e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void login(){
        User user = userService.login("huang1", "123");
        System.out.println(user);
    }


    @Test
    public void changePassword(){
        userService.changPassword(38,"huang5","123","321");
    }
    @Test
    public void getByUid(){
        System.out.println(userService.getByUid(38));
    }
    @Test
    public void changInfo(){
        User user = new User();
        userService.changInfo(38,"1111",user);
    }

}
