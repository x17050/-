package com.repair.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.repair.order.entity.SysWorker;
import org.apache.ibatis.annotations.Mapper;

/**
 * 维修工人Mapper接口
 */
@Mapper
public interface SysWorkerMapper extends BaseMapper<SysWorker> {

    // 基础CRUD由BaseMapper提供，无需额外编写
}
