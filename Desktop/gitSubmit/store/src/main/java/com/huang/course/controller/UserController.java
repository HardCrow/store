package com.huang.course.controller;

import com.huang.course.entity.User;
import com.huang.course.service.UserService;
import com.huang.course.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    @RequestMapping("/reg")
        public JsonResult<Void> reg(User user){
        userService.reg(user);
        return new JsonResult<>(OK);
    }
    @RequestMapping("/login")
    public JsonResult<User> login(String username, String password, HttpSession session){
    User data = userService.login(username, password);
    session.setAttribute("uid",data.getUid());
    session.setAttribute("username",data.getUsername());
    System.out.println( getuidFromSession(session));
    System.out.println( getUsernameFromSession(session));
    return new JsonResult<User>(OK,data);
    }
    @RequestMapping("/change_password")
    public JsonResult<User> change_password(String oldPassword, String newPassword, HttpSession session){
       Integer uid=getuidFromSession(session);
       String username =getUsernameFromSession(session);
       userService.changPassword(uid,username,oldPassword,newPassword);
        return new JsonResult<User>(OK);
    }
    @RequestMapping("/get_by_uid")
    public JsonResult<User> getByUid( HttpSession session){
        User data= userService.getByUid(getuidFromSession(session));
        return new JsonResult<User>(OK,data);
    }
}
