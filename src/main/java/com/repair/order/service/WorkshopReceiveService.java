package com.repair.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.repair.order.entity.WorkshopReceive;

public interface WorkshopReceiveService extends IService<WorkshopReceive> {
    // 根据订单ID查询接单记录
    WorkshopReceive getByOrderId(Long orderId);
}

