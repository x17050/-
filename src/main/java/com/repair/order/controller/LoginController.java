package com.repair.order.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.repair.order.common.Result;
import com.repair.order.entity.SysUser;
import com.repair.order.service.SysUserService;

/**
 * 登录接口
 */
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 登录接口（对接前端登录页面）
     * @param loginParam 用户名+密码
     * @return 登录结果+用户信息+角色
     */
    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody Map<String, String> loginParam) {
        String username = loginParam.get("username");
        String password = loginParam.get("password");

        // 1. 参数校验
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return Result.error("用户名和密码不能为空");
        }

        // 2. 调用Service验证登录
        SysUser sysUser = sysUserService.login(username, password);
        if (sysUser == null) {
            return Result.error("用户名或密码错误，或用户已被禁用");
        }

        // 3. 构造返回数据（隐藏密码）
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("userId", sysUser.getId());
        resultMap.put("username", sysUser.getUsername());
        resultMap.put("realName", sysUser.getRealName());
        resultMap.put("role", sysUser.getRole()); // 用户角色：user/workshop/admin

        return Result.success(resultMap);
    }
}
