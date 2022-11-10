package com.huang.course.entity;


import java.io.Serializable;
import java.util.Objects;

public class BaseEntity implements Serializable {
   private String createdUser;

   private String modifiedUser;


    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }


    public String getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser;
    }





    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntity)) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(getCreatedUser(), that.getCreatedUser()) &&

                Objects.equals(getModifiedUser(), that.getModifiedUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCreatedUser(), getModifiedUser());
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "createdUser='" + createdUser + '\'' +
                ", modifiedUser='" + modifiedUser + '\'' +
                '}';
    }
}
