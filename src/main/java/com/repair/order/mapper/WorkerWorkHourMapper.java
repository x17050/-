package com.repair.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.repair.order.entity.WorkerWorkHour;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 工人工作时长Mapper接口
 */
@Mapper
public interface WorkerWorkHourMapper extends BaseMapper<WorkerWorkHour> {

    // 自定义示例：根据工人ID和日期查询工时
    List<WorkerWorkHour> selectByWorkerIdAndDate(Long workerId, String workDate);
}