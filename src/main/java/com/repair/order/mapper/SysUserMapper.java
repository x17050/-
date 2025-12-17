package com.repair.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.repair.order.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户Mapper接口
 * 继承BaseMapper，自动拥有CRUD方法
 */
@Mapper // 标记为MyBatis Mapper接口（主类已加@MapperScan，此注解可选，建议保留）
public interface SysUserMapper extends BaseMapper<SysUser> {

    // 如需自定义SQL（如复杂查询），可在此添加方法并编写XML，基础CRUD无需编写
    // 示例：根据用户名查询用户（登录核心方法）
    SysUser selectByUsername(String username);
}
