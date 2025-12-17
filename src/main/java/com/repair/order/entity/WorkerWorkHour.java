package com.repair.order.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 工人工作时长实体类
 * 对应表：worker_work_hour
 */
@Data
@TableName("worker_work_hour")
public class WorkerWorkHour {

    /**
     * 工时记录ID（主键，自增）
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 维修工人ID（sys_worker.id）
     */
    private Long workerId;

    /**
     * 关联订单ID（repair_order.id）
     */
    private Long orderId;

    /**
     * 工作日期
     */
    private LocalDate workDate;

    /**
     * 工作时长（小时，保留1位小数）
     */
    private BigDecimal workHours;

    /**
     * 工时备注（如：拆机2小时，维修3小时）
     */
    private String workNote;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}