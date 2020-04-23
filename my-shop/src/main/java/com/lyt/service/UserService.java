package com.lyt.service;

import com.lyt.module.user.entity.User;

public interface UserService {

    Object listAll(int page, int size);

    int insert(User user);

    int remove(Integer userId);

    int update(User user);
}
