package com.repair.order.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.repair.order.entity.WorkshopReceive;
import com.repair.order.mapper.WorkshopReceiveMapper;
import com.repair.order.service.WorkshopReceiveService;

@Service
public class WorkshopReceiveServiceImpl extends ServiceImpl<WorkshopReceiveMapper, WorkshopReceive> implements WorkshopReceiveService {
    @Override
    public WorkshopReceive getByOrderId(Long orderId) {
        LambdaQueryWrapper<WorkshopReceive> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WorkshopReceive::getOrderId, orderId);
        return baseMapper.selectOne(queryWrapper);
    }
}