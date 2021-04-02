package com.lyt.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.lyt.config.WeChatConfig;
import com.lyt.module.user.dao.UserDao;
import com.lyt.module.user.dao.UserMapper;
import com.lyt.module.user.entity.User;
import com.lyt.service.UserService;
import com.lyt.util.HttpUtils;
import com.lyt.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
        for (int i = 0; i < 500000; i++) {
            User user = new User("骚桃子" + i, "password" + i, new Date(), new Date());
            list.add(user);
        }
        int size = list.size();
        if (size > 0) {
            try {
                Long start = System.currentTimeMillis();
                boolean flg = doBathAdd(list, pageSize);
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
        String str = "A";
        String str1 = "A|B|C|D|";
        System.out.println(JsonUtil.toJson(str.split("|")));
    }

    /**
     * 插入数据库处理
     * @param list          数据
     * @param pageSize      每次插入多少条
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public boolean doBathAdd(List<User> list, int pageSize) {
        boolean flg = false;
        try {
            // 把原始数据list按照规定的数目进行拆分
            List<List<User>> splitList = Lists.partition(list, pageSize);
            ExecutorService cache = Executors.newCachedThreadPool();
            CompletionService service = new ExecutorCompletionService(cache);
            for (int i = 0 ;i < splitList.size() ; i ++){
                final int index = i;
                service.submit(new Callable() {
                    @Override
                    public Object call() throws Exception {
                        return userDao.doBathAdd(splitList.get(index));
                    }
                });
            }
            int total = 0;
            for (int i =0 ;i < splitList.size(); i ++){
                Integer result = (Integer) service.take().get();
                total += result;
                log.info("第{}阶段执行成功！", i + 1);
            }
            if (total == list.size()) {
                System.out.println("所有数据执行成功！");
                flg = true;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return flg;
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




    @Autowired
    private WeChatConfig weChatConfig;


    @Override
    public User saveWeChatUser(String code) {

        //1、通过openAppid和openAppsecret和微信返回的code，拼接URL
        String accessTokenUrl = String.format(WeChatConfig.OPEN_ACCESS_TOKEN_URL, weChatConfig.getOpenAppid().trim(), weChatConfig.getOpenAppsecret().trim(), code);

        //2、通过URL再去回调微信接口 (使用了httpclient和gson工具）
        Map<String, Object> baseMap = HttpUtils.doGet(accessTokenUrl);

        //3、回调成功后获取access_token和oppid
        if (baseMap == null || baseMap.isEmpty()) return null;
        String accessToken = (String) baseMap.get("access_token");
        String openId = (String) baseMap.get("openid");
        System.err.println("accessToken： " + accessToken + ", openId：" + openId);
        //4、access_token和openid拼接URL（openid是用户唯一标志）
        String userInfoUrl = String.format(WeChatConfig.OPEN_USER_INFO_URL, accessToken, openId);

        //5、通过URL再去调微信接口获取用户基本信息
        Map<String, Object> baseUserMap = HttpUtils.doGet(userInfoUrl);

        if (baseUserMap == null || baseUserMap.isEmpty()) return null;

        //6、获取用户姓名、性别、城市、头像等基本信息
        String nickname = (String) baseUserMap.get("nickname");
        Integer sex = (Integer) baseUserMap.get("sex");
        String province = (String) baseUserMap.get("province");
        String city = (String) baseUserMap.get("city");
        String country = (String) baseUserMap.get("country");
        String headimgurl = (String) baseUserMap.get("headimgurl");

        return null;
    }
}
