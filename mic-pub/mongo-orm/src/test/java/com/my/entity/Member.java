package com.my.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * @author HXS
 * @copyright
 * @since 2019-03-05
 */
public class Member implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private ObjectId id;
    private String nickname;
    private String password;
    private int sex;
    private int age;

    public Member(String nickname, String password, int sex, int age) {
        this.nickname = nickname;
        this.password = password;
        this.sex = sex;
        this.age = age;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                '}';
    }
}
