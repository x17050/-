package com.repair.order.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.repair.order.entity.SysWorker;
import com.repair.order.mapper.SysWorkerMapper;
import com.repair.order.service.SysWorkerService;

@Service
public class SysWorkerServiceImpl extends ServiceImpl<SysWorkerMapper, SysWorker> implements SysWorkerService {
    @Override
    public List<SysWorker> getWorkerListByWorkshopUserId(Long workshopUserId) {
        LambdaQueryWrapper<SysWorker> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysWorker::getWorkshopUserId, workshopUserId)
                    .eq(SysWorker::getStatus, 1); // 只查在职工人
        return baseMapper.selectList(queryWrapper);
    }
}