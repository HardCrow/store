package com.atguigu.ssm.service;

import com.atguigu.ssm.mapper.EmployeeMapper;
import com.atguigu.ssm.pojo.Employee;
import com.atguigu.ssm.pojo.User;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    List<Employee> getAllEmployee();


    //自己写的
    int insertEmployee(Employee employee);

    //登入页面查询
   User check(User user);
   //登入的第二种写法
    Map checks(Map<String,Object> map);
    //搜索引擎功能
    List<Employee> reach(String empName);
}
