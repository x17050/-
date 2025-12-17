package com.repair.order.service.impl;

import java.util.List;
import static java.util.regex.Pattern.matches;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.repair.order.entity.SysUser;
import com.repair.order.mapper.SysUserMapper;
import com.repair.order.service.SysUserService;

/**
 * 系统用户业务实现类
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    // BCrypt加密器（Spring Security提供）
    // private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * 登录验证逻辑
     */
    @Override
    public SysUser login(String username, String password) {
        // 1. 根据用户名查询用户
        SysUser sysUser = getByUsername(username);
        if (sysUser == null) {
            return null; // 用户名不存在
        }

        // 2. 校验密码（数据库存储加密密码，前端传明文）
        if (!matches(password, sysUser.getPassword())) {
            return null; // 密码错误
        }

        // 3. 校验用户状态（1-正常，0-禁用）
        if (sysUser.getStatus() != 1) {
            return null; // 用户被禁用
        }

        return sysUser; // 登录成功
    }

    /**
     * 根据用户名查询用户
     */
    @Override
    public SysUser getByUsername(String username) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUsername, username);
        return baseMapper.selectOne(queryWrapper); // baseMapper = SysUserMapper
    }

    /**
     * 列表查询（实现接口中声明的list方法）
     */
    @Override
    public List<SysUser> list() {
        return super.list();
    }
}
