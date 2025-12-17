package com.repair.order.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.repair.order.entity.SysWorker;

public interface SysWorkerService extends IService<SysWorker> {
    // 根据车间人员ID查询工人列表
    List<SysWorker> getWorkerListByWorkshopUserId(Long workshopUserId);
}
