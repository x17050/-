package com.repair.order.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.repair.order.entity.WorkerWorkHour;
import com.repair.order.mapper.WorkerWorkHourMapper;
import com.repair.order.service.WorkerWorkHourService;

@Service
public class WorkerWorkHourServiceImpl extends ServiceImpl<WorkerWorkHourMapper, WorkerWorkHour> implements WorkerWorkHourService {
    @Override
    public List<WorkerWorkHour> getWorkHourListByWorkerId(Long workerId) {
        LambdaQueryWrapper<WorkerWorkHour> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WorkerWorkHour::getWorkerId, workerId)
                    .orderByDesc(WorkerWorkHour::getWorkDate);
        return baseMapper.selectList(queryWrapper);
    }
}
