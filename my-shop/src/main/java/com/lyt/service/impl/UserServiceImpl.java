package com.lyt.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyt.module.user.dao.UserDao;
import com.lyt.module.user.dao.UserMapper;
import com.lyt.module.user.entity.User;
import com.lyt.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author liyintao
 * @Date 2020/4/23 10:48 PM
 */
@Slf4j
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
    public JSONObject xmlInsert(Integer pageSize) {
        JSONObject resultJson = new JSONObject();
        List<User> list = new ArrayList<>();
        // 静态数据
        for (int i = 0; i < 50000; i++) {
            User user = new User("骚桃子" + i, "password" + i, new Date(), new Date());
            list.add(user);
        }
        int size = list.size();

        if (size > 0) {
            int pageCount = 0 ;
            if (size % pageSize == 0){
                pageCount = size / pageSize;
            }else {
                pageCount = size / pageSize + 1;
            }
            try {
                Long start = System.currentTimeMillis();
                boolean flg = doBathAdd(list, pageCount, pageSize);
                Long end = System.currentTimeMillis();
                log.info("插入数据总量：{}，总共耗时：{}", size, end-start);
                resultJson.put("success", flg);
//                resultJson.put("error", size - num);
            } catch (Exception e) {
                log.error("插入异常！");
                e.printStackTrace();
            }

        }
        return resultJson;
    }

    public static void main(String[] args){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 50000; i++) {
            list.add(i);
        }
        int size = list.size();
        int pageSize = 10000;
        int pageCount = 0 ;
        if (size % pageSize == 0){
            pageCount = size / pageSize;
        }else {
            pageCount = size / pageSize + 1;
        }
        try {
            doBathAddTest(list, pageCount, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 插入数据库处理
     * @param list          数据
     * @param pageCount     需要几次
     * @param pageSize      每次插入多少条
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public boolean doBathAdd(List<User> list, int pageCount, int pageSize) {
        boolean flg = true;

        try {
            ExecutorService cache = Executors.newCachedThreadPool();
            CompletionService service = new ExecutorCompletionService(cache);
            final int num = 0;
            for (int i = 0 ;i < pageCount ; i ++){
                final int index = i;
                service.submit(new Callable() {
                    @Override
                    public Object call() throws Exception {
                        List subList = list.subList(index * pageSize,(index + 1) * pageSize);
                        log.info("当前次数：{}，subList长度：{}", (index + 1), subList.size());
                        return userDao.doBathAdd(list);
                    }
                });
            }
            for (int i =0 ;i < pageCount; i ++){
                Integer result = (Integer) service.take().get();
                if ("1".equals(result)) {
                    flg = false;
                    break;
                }
                System.out.println("所有数据执行成功！");
            }
        } catch (InterruptedException e) {
            flg = false;
            e.printStackTrace();
        } catch (ExecutionException e) {
            flg = false;
            e.printStackTrace();
        }
        return flg;
    }

    public static void doBathAddTest(List<Integer> list, int pageCount, int pageSize) throws InterruptedException, ExecutionException {
        ExecutorService cache = Executors.newCachedThreadPool();
        CompletionService service = new ExecutorCompletionService(cache);
        for (int i = 0 ;i < pageCount ; i ++){
            final int index = i;
            service.submit(new Callable() {
                @Override
                public Object call() throws Exception {
                    List subList = list.subList(index * pageSize,(index + 1) * pageSize);
                    log.info("subList长度：{}", subList.size());
                    return subList.size() + "";
                }
            });
        }

        for (int i =0 ;i < pageCount; i ++){
            String result = (String) service.take().get();
            System.out.println("result = " + result);
        }
    }
    @Override
    public int remove(Integer userId, String type) {
        if ("0".equals(type)) {
            return userMapper.removeAll();
        } else if ("1".equals(type)) {
            return userMapper.remove(userId);
        }
        return 0;
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }
}
