package com.lyt.service;

import com.alibaba.fastjson.JSONObject;
import com.lyt.module.user.entity.User;

import java.util.List;

public interface UserService {

    Object listAll(int page, int size);

    JSONObject xmlListAll(int page, int size, String userName);

    JSONObject xmlFindByIds(int page, int size, List<Integer> ids);

    int insert(User user);

    int remove(Integer userId);

    int update(User user);
}
