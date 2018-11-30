package com.gupao.domain.service;

import com.gupao.domain.user.User;

import java.util.Collection;

public interface UserService {
    boolean save(User user);

    Collection<User> findAll();
}
