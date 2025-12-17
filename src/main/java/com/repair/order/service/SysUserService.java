package com.repair.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.repair.order.entity.SysUser;
import java.util.List;

/**
 * 系统用户业务接口
 * 继承IService，获得通用CRUD方法
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 登录验证
     * @param username 用户名
     * @param password 密码（明文）
     * @return 登录成功返回用户信息，失败返回null
     */
    SysUser login(String username, String password);

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户信息
     */
    SysUser getByUsername(String username);

    /**
     * 查询所有用户列表
     * @return 用户列表
     */
    List<SysUser> list();
}
