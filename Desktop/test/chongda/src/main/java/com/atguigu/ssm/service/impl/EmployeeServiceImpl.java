package com.atguigu.ssm.service.impl;

import com.atguigu.ssm.mapper.EmployeeMapper;
import com.atguigu.ssm.pojo.Employee;
import com.atguigu.ssm.pojo.User;
import com.atguigu.ssm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class  EmployeeServiceImpl implements EmployeeService {
@Autowired    //相当于在这个类里面创建EmployeeMapper对象
private EmployeeMapper employeeMapper;

    public List<Employee> getAllEmployee() {

        return employeeMapper.getAllEmployee();
    }
    //自己写得
    public int insertEmployee(Employee employee) {
        return  employeeMapper.insertEmployee(employee);
    }


    //登入查询功能
    public User check(User user){
        return employeeMapper.check(user);
    }
//登入2
    public Map<String,Object> checks(Map<String,Object> map){
        return employeeMapper.checks(map);
    }
    //搜索引擎功能
    public List<Employee> reach(String empName){
        return employeeMapper.reach(empName);
    };
}
