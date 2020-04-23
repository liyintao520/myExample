package com.lyt.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyt.module.user.dao.UserMapper;
import com.lyt.module.user.entity.User;
import com.lyt.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author liyintao
 * @Date 2020/4/23 10:48 PM
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public Object listAll(int page, int size) {
        PageHelper.startPage(page, size);
        List<User> userList = userMapper.listAll();
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return pageInfo;
    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int remove(Integer userId) {
        return userMapper.remove(userId);
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }
}
