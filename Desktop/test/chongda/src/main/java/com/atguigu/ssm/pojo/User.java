package com.atguigu.ssm.pojo;

public class User {
    private Integer userid;
    private Integer password;

    public User() {
    }

    public User(Integer userid, Integer password) {
        this.userid = userid;
        this.password = password;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", password=" + password +
                '}';
    }
}
