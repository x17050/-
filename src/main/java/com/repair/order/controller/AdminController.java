package com.repair.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.repair.order.common.Result;
import com.repair.order.entity.SysUser;
import com.repair.order.service.SysUserService;

/**
 * 管理员接口（用户管理/订单管理）
 */
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 查询所有用户（管理员权限）
     * @return 用户列表
     */
    @GetMapping("/user/list")
    public Result<List<SysUser>> getAllUser() {
        List<SysUser> userList = sysUserService.list();
        // 隐藏密码
        userList.forEach(user -> user.setPassword(null));
        return Result.success(userList);
    }
}