package com.gupaoedu.user.dto;

import com.gupaoedu.user.abs.AbstractResponse;

public class UserLoginResponse extends AbstractResponse {
    private static final long serialVersionUID = 1555495083595583967L;

    private Integer uid;
    private String avatar;
    private String mobile;
    private String token;
    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserLoginResponse{" +
                "uid=" + uid +
                ", avatar='" + avatar + '\'' +
                ", mobile='" + mobile + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
