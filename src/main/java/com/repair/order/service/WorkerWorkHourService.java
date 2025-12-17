package com.repair.order.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.repair.order.entity.WorkerWorkHour;

public interface WorkerWorkHourService extends IService<WorkerWorkHour> {
    // 根据工人ID查询工时列表
    List<WorkerWorkHour> getWorkHourListByWorkerId(Long workerId);
}