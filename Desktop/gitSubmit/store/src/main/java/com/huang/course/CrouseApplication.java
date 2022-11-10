package com.huang.course;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.huang.course.mapper")
public class CrouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrouseApplication.class, args);
    }

}
