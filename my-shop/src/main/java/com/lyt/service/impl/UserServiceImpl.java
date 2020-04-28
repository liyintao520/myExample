package com.lyt.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyt.module.user.dao.UserDao;
import com.lyt.module.user.dao.UserMapper;
import com.lyt.module.user.entity.User;
import com.lyt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserDao userDao;

    @Override
    public Object listAll(int page, int size) {
        PageHelper.startPage(page, size);
        List<User> userList = userMapper.listAll();
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return pageInfo;
    }

    @Override
    public JSONObject xmlListAll(int page, int size, String userName) {
        JSONObject resultJson = new JSONObject();
        List<User> list = userDao.findUserByName(userName);
        PageInfo<User> info = new PageInfo<>(list);
        resultJson.put("data", list);
        resultJson.put("recordsTotal", info.getTotal());
        return resultJson;
    }

    @Override
    public JSONObject xmlFindByIds(int page, int size, List<Integer> ids) {
        JSONObject resultJson = new JSONObject();
        List<User> list = userDao.findByIds(ids);
        PageInfo<User> info = new PageInfo<>(list);
        resultJson.put("data", list);
        resultJson.put("recordsTotal", info.getTotal());
        return resultJson;
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
