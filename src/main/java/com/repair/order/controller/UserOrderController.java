package com.repair.order.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.repair.order.common.Result;
import com.repair.order.entity.RepairOrder;
import com.repair.order.service.RepairOrderService;

/**
 * 用户下单/个人订单列表接口
 */
@RestController
@RequestMapping("/api/order")
public class UserOrderController {

    @Autowired
    private RepairOrderService repairOrderService;

    /**
     * 创建维修订单（用户下单）
     * @param orderParam 订单参数
     * @return 订单ID
     */
    @PostMapping("/create")
    public Result<Long> createOrder(@RequestBody Map<String, Object> orderParam) {
        try {
            // 1. 解析参数
            Long userId = Long.valueOf(orderParam.get("userId").toString());
            String repairType = (String) orderParam.get("repairType");
            String faultDesc = (String) orderParam.get("faultDesc");
            String contactAddress = (String) orderParam.get("contactAddress");
            String contactPhone = (String) orderParam.get("contactPhone");

            // 2. 参数校验
            if (repairType == null || repairType.isEmpty() || contactPhone == null || contactPhone.isEmpty()) {
                return Result.error("维修类型和联系电话不能为空");
            }

            // 3. 构造订单对象
            RepairOrder repairOrder = new RepairOrder();
            repairOrder.setUserId(userId);
            repairOrder.setRepairType(repairType);
            repairOrder.setFaultDesc(faultDesc);
            repairOrder.setContactAddress(contactAddress);
            repairOrder.setContactPhone(contactPhone);

            // 4. 调用Service创建订单
            Long orderId = repairOrderService.createOrder(repairOrder);
            if (orderId == -1L) {
                return Result.error("创建订单失败");
            }

            return Result.success(orderId);
        } catch (Exception e) {
            return Result.error("创建订单异常：" + e.getMessage());
        }
    }

    /**
     * 查询个人订单列表
     * @param userId 用户ID
     * @return 订单列表
     */
    @GetMapping("/list")
    public Result<List<RepairOrder>> getOrderList(@RequestParam Long userId) {
        List<RepairOrder> orderList = repairOrderService.getOrderListByUserId(userId);
        return Result.success(orderList);
    }
}