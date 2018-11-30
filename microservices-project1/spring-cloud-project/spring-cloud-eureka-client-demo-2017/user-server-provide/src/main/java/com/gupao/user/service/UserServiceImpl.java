package com.gupao.user.service;

import com.gupao.domain.service.UserService;
import com.gupao.domain.user.User;
/**
 * ${@link UserService 用户服务} 提供者实现
 */
import java.util.Collection;

public class UserServiceImpl implements UserService {
    @Override
    public boolean save(User user) {
        return false;
    }

    @Override
    public Collection<User> findAll() {
        return null;
    }
}
