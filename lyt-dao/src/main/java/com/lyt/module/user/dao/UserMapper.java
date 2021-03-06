package com.lyt.module.user.dao;

import com.lyt.module.user.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @InterfaceName UserMapper
 * @Description 利用注解查询
 * @Author liyintao
 * @Date 2020/4/24 10:21
 */
@Mapper
public interface UserMapper {

//    @Select({
//            "select id as id, USER_NAME as userName, PASS_WORD as passWord, CREATE_TIME as createTime, UPDATE_TIME as updateTime from l_user"
//    })
    @Select({"select * from l_user"})
    @Results(id="userMap",  value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "USER_NAME", property = "userName"),
            @Result(column = "PASS_WORD", property = "passWord"),
            @Result(column = "CREATE_TIME", property = "createTime"),
            @Result(column = "UPDATE_TIME", property = "updateTime"),
    })
    List<User> listAll();

    @Insert({
            "insert into l_user(`USER_NAME`, `PASS_WORD`, `CREATE_TIME`, `UPDATE_TIME`) values(#{userName}, #{passWord}, NOW(), NOW())"
    })
    int insert(User user);

    /**
     * 根据id删除
     * @param userId
     * @return
     */
    @Delete({"delete from l_user where id = #{userId}"})
    int remove(Integer userId);

    /**
     * 删除全部
     * @return
     */
    @Delete({"delete from l_user"})
    int removeAll();

    @Update({
            "update l_user set USER_NAME = #{userName}, PASS_WORD = #{passWord} where id = #{id}"
    })
    int update(User user);

}
