package com.repair.order.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.repair.order.entity.RepairOrder;
import com.repair.order.mapper.RepairOrderMapper;
import com.repair.order.service.RepairOrderService;

/**
 * 维修订单业务实现类
 */
@Service
public class RepairOrderServiceImpl extends ServiceImpl<RepairOrderMapper, RepairOrder> implements RepairOrderService {

    /**
     * 创建订单（核心逻辑：生成唯一订单编号）
     */
    @Override
    public Long createOrder(RepairOrder repairOrder) {
        // 1. 生成订单编号：YYYYMMDD + 6位随机数
        LocalDateTime now = LocalDateTime.now();
        String dateStr = now.getYear() + 
                         String.format("%02d", now.getMonthValue()) + 
                         String.format("%02d", now.getDayOfMonth());
        String randomStr = String.format("%06d", new Random().nextInt(999999));
        String orderNo = dateStr + randomStr;

        // 2. 填充订单默认值
        repairOrder.setOrderNo(orderNo);
        repairOrder.setOrderStatus("pending"); // 初始状态：待接单

        // 3. 保存订单
        boolean saveSuccess = save(repairOrder);
        return saveSuccess ? repairOrder.getId() : -1L;
    }

    /**
     * 根据用户ID查询订单
     */
    @Override
    public List<RepairOrder> getOrderListByUserId(Long userId) {
        LambdaQueryWrapper<RepairOrder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RepairOrder::getUserId, userId)
                    .orderByDesc(RepairOrder::getCreateTime); // 按创建时间倒序
        return baseMapper.selectList(queryWrapper);
    }

    /**
     * 根据状态查询订单
     */
    @Override
    public List<RepairOrder> getOrderListByStatus(String orderStatus) {
        LambdaQueryWrapper<RepairOrder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RepairOrder::getOrderStatus, orderStatus)
                    .orderByDesc(RepairOrder::getCreateTime);
        return baseMapper.selectList(queryWrapper);
    }

    /**
     * 更新订单状态
     */
    @Override
    public boolean updateOrderStatus(Long orderId, String status) {
        RepairOrder repairOrder = new RepairOrder();
        repairOrder.setId(orderId);
        repairOrder.setOrderStatus(status);
        return updateById(repairOrder);
    }
}
