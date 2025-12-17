package com.repair.order.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.repair.order.entity.RepairOrder;

/**
 * 维修订单业务接口
 */
public interface RepairOrderService extends IService<RepairOrder> {

    /**
     * 创建维修订单（自动生成订单编号）
     * @param repairOrder 订单信息
     * @return 创建成功返回订单ID，失败返回-1
     */
    Long createOrder(RepairOrder repairOrder);

    /**
     * 根据用户ID查询订单列表
     * @param userId 用户ID
     * @return 订单列表
     */
    List<RepairOrder> getOrderListByUserId(Long userId);

    /**
     * 根据订单状态查询订单列表
     * @param orderStatus 订单状态
     * @return 订单列表
     */
    List<RepairOrder> getOrderListByStatus(String orderStatus);

    /**
     * 更新订单状态
     * @param orderId 订单ID
     * @param status 新状态
     * @return 是否成功
     */
    boolean updateOrderStatus(Long orderId, String status);
}