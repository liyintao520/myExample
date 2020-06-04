package com.lyt.controller;

import com.alibaba.fastjson.JSONObject;
import com.lyt.module.user.entity.User;
import com.lyt.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName UserController
 * @Description 测试连接数据库
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
     * 查询全部     利用XML查询
     * http://localhost:8020/xmlFindByIds
     *  注意：PostMan调用的时候，用post方法。选择Body--》raw 参数是： [1,2,3], 最后一项选择 JSON
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/xmlFindByIds")
    public JSONObject xmlFindByIds(@RequestParam(value = "page",defaultValue = "1")int page,
                                 @RequestParam(value = "size",defaultValue = "10")int size,
                                 @RequestBody List<Integer> ids){
        return userService.xmlFindByIds(page, size, ids);
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
