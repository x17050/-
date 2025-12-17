package com.repair.order.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 车间接单记录实体类
 * 对应表：workshop_receive
 */
@Data
@TableName("workshop_receive")
public class WorkshopReceive {

    /**
     * 接单记录ID（主键，自增）
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 关联订单ID（repair_order.id）
     */
    private Long orderId;

    /**
     * 接单的车间人员ID（sys_user.id）
     */
    private Long workshopUserId;

    /**
     * 指派的维修工人ID（sys_worker.id）
     */
    private Long workerId;

    /**
     * 接单时间
     */
    private LocalDateTime receiveTime;

    /**
     * 维修过程备注
     */
    private String repairNote;

    /**
     * 维修完成时间
     */
    private LocalDateTime finishTime;

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