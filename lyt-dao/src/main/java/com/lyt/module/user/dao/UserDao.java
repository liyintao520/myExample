package com.lyt.module.user.dao;

import com.lyt.module.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @InterfaceName UserDao
 * @Description 利用xml查询
 * @Author liyintao
 * @Date 2020/4/24 10:21
 */
@Mapper
public interface UserDao {
    /**
     * 通过名字查询用户信息
     */
    List<User> findUserByName(@Param("userName") String userName);

    /**
     * 批量查询
     * @param ids
     * @return
     */
    List<User> findByIds(@Param("ids")List<Integer> ids);
}