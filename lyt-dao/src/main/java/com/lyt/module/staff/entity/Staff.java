package com.lyt.module.staff.entity;

import lombok.Data;

/**
 * @ClassName Staff
 * @Description 员工实体类
 * @Author liyintao
 * @Date 2020/7/22 10:07
 */
@Data
public class Staff {
    /**
     * 主键ID 序号
     */
    private Integer id;

    /**
     * 工厂
     */
    private String factory;

    /**
     * 部门及工种
     */
    private String dept;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 离职类型
     */
    private String type;

    /**
     * 离职原因
     */
    private String reasons;

    /**
     * 原因计数
     */
    private Integer count;
}
