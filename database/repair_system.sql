#sql
CREATE DATABASE `repair_order_system` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
-- repair_order_system.repair_order definition
#维修订单表
CREATE TABLE `repair_order` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '订单ID（主键）',
  `order_no` varchar(32) NOT NULL COMMENT '订单编号',
  `user_id` bigint unsigned NOT NULL COMMENT '下单用户ID',
  `repair_type` varchar(50) NOT NULL COMMENT '维修类型',
  `fault_desc` text COMMENT '故障描述',
  `contact_address` varchar(200) DEFAULT NULL COMMENT '维修地址',
  `contact_phone` varchar(20) NOT NULL COMMENT '联系电话',
  `order_status` enum('pending','receiving','repairing','finished','cancelled') NOT NULL DEFAULT 'pending' COMMENT '订单状态',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_order_no` (`order_no`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_order_status` (`order_status`),
  CONSTRAINT `repair_order_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='维修订单表';

-- repair_order_system.sys_user definition
#系统用户表
CREATE TABLE `sys_user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '用户ID（主键）',
  `username` varchar(50) NOT NULL COMMENT '登录用户名（唯一）',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `real_name` varchar(20) DEFAULT NULL COMMENT '真实姓名',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `role` enum('user','workshop','admin') NOT NULL DEFAULT 'user' COMMENT '角色：user-普通用户，workshop-车间人员，admin-管理员',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态：1-正常，0-禁用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_username` (`username`),
  KEY `idx_role` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统用户表';
#维修工人表
-- repair_order_system.sys_worker definition

CREATE TABLE `sys_worker` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '工人ID（主键）',
  `workshop_user_id` bigint unsigned NOT NULL COMMENT '关联车间人员ID（sys_user.id）',
  `worker_name` varchar(20) NOT NULL COMMENT '工人姓名',
  `worker_no` varchar(20) DEFAULT NULL COMMENT '工号',
  `skill` varchar(100) DEFAULT NULL COMMENT '擅长维修类型',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态：1-在职，0-离职',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `worker_no` (`worker_no`),
  KEY `idx_workshop_user_id` (`workshop_user_id`),
  CONSTRAINT `sys_worker_ibfk_1` FOREIGN KEY (`workshop_user_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='维修工人表';
#工人时长表
-- repair_order_system.worker_work_hour definition

CREATE TABLE `worker_work_hour` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '工时记录ID（主键）',
  `worker_id` bigint unsigned NOT NULL COMMENT '维修工人ID',
  `order_id` bigint unsigned NOT NULL COMMENT '关联订单ID',
  `work_date` date NOT NULL COMMENT '工作日期',
  `work_hours` decimal(4,1) NOT NULL COMMENT '工作时长（小时）',
  `work_note` varchar(200) DEFAULT NULL COMMENT '工时备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_worker_id` (`worker_id`),
  KEY `idx_order_id` (`order_id`),
  KEY `idx_work_date` (`work_date`),
  CONSTRAINT `worker_work_hour_ibfk_1` FOREIGN KEY (`worker_id`) REFERENCES `sys_worker` (`id`) ON DELETE CASCADE,
  CONSTRAINT `worker_work_hour_ibfk_2` FOREIGN KEY (`order_id`) REFERENCES `repair_order` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='工人工作时长表';
#接单记录表
-- repair_order_system.workshop_receive definition

CREATE TABLE `workshop_receive` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '接单记录ID（主键）',
  `order_id` bigint unsigned NOT NULL COMMENT '关联订单ID',
  `workshop_user_id` bigint unsigned NOT NULL COMMENT '接单的车间人员ID',
  `worker_id` bigint unsigned DEFAULT NULL COMMENT '指派的维修工人ID',
  `receive_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '接单时间',
  `repair_note` text COMMENT '维修过程备注',
  `finish_time` datetime DEFAULT NULL COMMENT '维修完成时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_order_id` (`order_id`),
  KEY `idx_workshop_user_id` (`workshop_user_id`),
  KEY `idx_worker_id` (`worker_id`),
  CONSTRAINT `workshop_receive_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `repair_order` (`id`) ON DELETE CASCADE,
  CONSTRAINT `workshop_receive_ibfk_2` FOREIGN KEY (`workshop_user_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `workshop_receive_ibfk_3` FOREIGN KEY (`worker_id`) REFERENCES `sys_worker` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='车间接单记录表';
