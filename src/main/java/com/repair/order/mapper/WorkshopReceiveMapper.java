package com.repair.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.repair.order.entity.WorkshopReceive;
import org.apache.ibatis.annotations.Mapper;

/**
 * 车间接单记录Mapper接口
 */
@Mapper
public interface WorkshopReceiveMapper extends BaseMapper<WorkshopReceive> {

    // 基础CRUD由BaseMapper提供
}
