package com.lyt.controller;

import com.alibaba.fastjson.JSONObject;
import com.lyt.module.user.entity.User;
import com.lyt.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author liyintao
 * @Date 2020/4/23 10:46 PM
 */
@RestController
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 查询全部     使用注解查询
     * http://localhost:8020/listAll
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/listAll")
    public Object listAll(@RequestParam(value = "page",defaultValue = "1")int page,
                          @RequestParam(value = "size",defaultValue = "10")int size){
        return userService.listAll(page, size);
    }

    /**
     * 查询全部     利用XML查询
     * http://localhost:8020/xmlListAll?userName=liyintao
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/xmlListAll")
    public JSONObject xmlListAll(@RequestParam(value = "page",defaultValue = "1")int page,
                                 @RequestParam(value = "size",defaultValue = "10")int size,
                                 @RequestParam(value = "userName") String userName){
        return userService.xmlListAll(page, size, userName);
    }

    /**
     * 添加数据
     * @param user
     * @return
     */
    @PostMapping("/insert")
    public int insert (User user){
        return userService.insert(user);
    }

    /**
     * 删除
     * @param userId
     * @return
     */
    @PostMapping("/remove")
    public int remove(Integer userId){
        return userService.remove(userId);
    }

    /**
     * 修改
     * @param user
     * @return
     */
    @PostMapping("/update")
    public int update(User user){
        return userService.update(user);
    }

}
