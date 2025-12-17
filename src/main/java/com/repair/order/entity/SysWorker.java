package com.repair.order.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 维修工人实体类
 * 对应表：sys_worker
 */
@Data
@TableName("sys_worker")
public class SysWorker {

    /**
     * 工人ID（主键，自增）
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 关联车间人员ID（sys_user.id）
     */
    private Long workshopUserId;

    /**
     * 工人姓名
     */
    private String workerName;

    /**
     * 工号（唯一）
     */
    private String workerNo;

    /**
     * 擅长维修类型（如：空调、冰箱维修）
     */
    private String skill;

    /**
     * 状态：1-在职，0-离职
     */
    private Integer status;

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