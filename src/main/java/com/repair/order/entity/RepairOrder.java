package com.repair.order.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 维修订单实体类
 * 对应表：repair_order
 */
@Data
@TableName("repair_order")
public class RepairOrder {

    /**
     * 订单ID（主键，自增）
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 订单编号（唯一）
     */
    private String orderNo;

    /**
     * 下单用户ID（sys_user.id）
     */
    private Long userId;

    /**
     * 维修类型（如：空调、冰箱、汽车发动机）
     */
    private String repairType;

    /**
     * 故障描述
     */
    private String faultDesc;

    /**
     * 维修地址
     */
    private String contactAddress;

    /**
     * 联系电话
     */
    private String contactPhone;

    /**
     * 订单状态：pending-待接单，receiving-已接单，repairing-维修中，finished-已完成，cancelled-已取消
     */
    private String orderStatus;

    /**
     * 下单时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}