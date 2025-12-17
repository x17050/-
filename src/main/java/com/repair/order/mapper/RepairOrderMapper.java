package com.repair.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.repair.order.entity.RepairOrder;

/**
 * 维修订单Mapper接口
 */
@Mapper
public interface RepairOrderMapper extends BaseMapper<RepairOrder> {

    // 自定义示例：根据用户ID查询订单列表
    List<RepairOrder> selectByUserId(Long userId);

    // 自定义示例：根据订单状态查询订单列表
    List<RepairOrder> selectByOrderStatus(String orderStatus);
}
