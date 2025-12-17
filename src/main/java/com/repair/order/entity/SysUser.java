package com.repair.order.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 系统用户实体类
 * 对应表：sys_user
 */
@Data
@TableName("sys_user") // 指定数据库表名
public class SysUser {

    /**
     * 用户ID（主键，自增）
     */
    @TableId(type = IdType.AUTO) // 主键自增（匹配数据库AUTO_INCREMENT）
    private Long id;

    /**
     * 登录用户名（唯一）
     */
    private String username;

    /**
     * 密码（BCrypt加密）
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 角色：user-普通用户，workshop-车间人员，admin-管理员
     */
    private String role;

    /**
     * 状态：1-正常，0-禁用
     */
    private Integer status;

    /**
     * 创建时间（自动填充）
     */
    @TableField(fill = FieldFill.INSERT) // 插入时自动填充
    private LocalDateTime createTime;

    /**
     * 更新时间（自动填充）
     */
    @TableField(fill = FieldFill.INSERT_UPDATE) // 插入/更新时自动填充
    private LocalDateTime updateTime;
}