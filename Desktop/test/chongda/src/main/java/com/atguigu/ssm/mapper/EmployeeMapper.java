package com.atguigu.ssm.mapper;

import com.atguigu.ssm.pojo.Employee;
import com.atguigu.ssm.pojo.User;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {
    List<Employee> getAllEmployee();

    //自己写得
    int insertEmployee(Employee employee);

    //登入查询
    User check(User user);
    //登入的第二种写法
    Map checks(Map<String,Object> map);
    //搜索引擎
    List<Employee> reach(String empName);
}
