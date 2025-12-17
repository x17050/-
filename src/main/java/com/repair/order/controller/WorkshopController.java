package com.repair.order.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
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
import com.repair.order.entity.WorkerWorkHour;
import com.repair.order.entity.WorkshopReceive;
import com.repair.order.service.RepairOrderService;
import com.repair.order.service.WorkerWorkHourService;
import com.repair.order.service.WorkshopReceiveService;

/**
 * 车间接单/维修状态/工人工时接口
 */
@RestController
@RequestMapping("/api/workshop")
public class WorkshopController {

    @Autowired
    private RepairOrderService repairOrderService;

    @Autowired
    private WorkshopReceiveService workshopReceiveService;

    @Autowired
    private WorkerWorkHourService workerWorkHourService;

    /**
     * 查询待接单订单列表
     * @return 待接单订单
     */
    @GetMapping("/pending/orders")
    public Result<List<RepairOrder>> getPendingOrders() {
        List<RepairOrder> pendingOrders = repairOrderService.getOrderListByStatus("pending");
        return Result.success(pendingOrders);
    }

    /**
     * 车间接单
     * @param receiveParam 接单参数（订单ID+车间人员ID+工人ID）
     * @return 是否成功
     */
    @PostMapping("/receive/order")
    public Result<Boolean> receiveOrder(@RequestBody Map<String, Object> receiveParam) {
        try {
            Long orderId = Long.valueOf(receiveParam.get("orderId").toString());
            Long workshopUserId = Long.valueOf(receiveParam.get("workshopUserId").toString());
            Long workerId = receiveParam.get("workerId") != null ? 
                            Long.valueOf(receiveParam.get("workerId").toString()) : null;

            // 1. 检查订单是否已被接单
            WorkshopReceive existReceive = workshopReceiveService.getByOrderId(orderId);
            if (existReceive != null) {
                return Result.error("该订单已被接单，无法重复接单");
            }

            // 2. 创建接单记录
            WorkshopReceive workshopReceive = new WorkshopReceive();
            workshopReceive.setOrderId(orderId);
            workshopReceive.setWorkshopUserId(workshopUserId);
            workshopReceive.setWorkerId(workerId);
            workshopReceive.setReceiveTime(LocalDate.now().atStartOfDay()); // 接单时间

            boolean saveSuccess = workshopReceiveService.save(workshopReceive);
            if (!saveSuccess) {
                return Result.error("接单失败");
            }

            // 3. 更新订单状态为“已接单”
            boolean updateSuccess = repairOrderService.updateOrderStatus(orderId, "receiving");

            return Result.success(updateSuccess);
        } catch (Exception e) {
            return Result.error("接单异常：" + e.getMessage());
        }
    }

    /**
     * 更新订单维修状态（维修中/已完成）
     * @param statusParam 订单ID+新状态
     * @return 是否成功
     */
    @PostMapping("/update/status")
    public Result<Boolean> updateOrderStatus(@RequestBody Map<String, Object> statusParam) {
        Long orderId = Long.valueOf(statusParam.get("orderId").toString());
        String status = (String) statusParam.get("status");

        // 校验状态合法性
        if (!"receiving".equals(status) && !"repairing".equals(status) && !"finished".equals(status) && !"cancelled".equals(status)) {
            return Result.error("无效的订单状态");
        }

        boolean success = repairOrderService.updateOrderStatus(orderId, status);
        return success ? Result.success(true) : Result.error("更新状态失败");
    }

    /**
     * 记录工人工作时长
     * @param hourParam 工时参数
     * @return 是否成功
     */
    @PostMapping("/add/work/hour")
    public Result<Boolean> addWorkHour(@RequestBody Map<String, Object> hourParam) {
        try {
            Long workerId = Long.valueOf(hourParam.get("workerId").toString());
            Long orderId = Long.valueOf(hourParam.get("orderId").toString());
            String workDate = (String) hourParam.get("workDate");
            BigDecimal workHours = new BigDecimal(hourParam.get("workHours").toString());
            String workNote = (String) hourParam.get("workNote");

            // 构造工时对象
            WorkerWorkHour workHour = new WorkerWorkHour();
            workHour.setWorkerId(workerId);
            workHour.setOrderId(orderId);
            workHour.setWorkDate(LocalDate.parse(workDate));
            workHour.setWorkHours(workHours);
            workHour.setWorkNote(workNote);

            boolean saveSuccess = workerWorkHourService.save(workHour);
            return saveSuccess ? Result.success(true) : Result.error("记录工时失败");
        } catch (Exception e) {
            return Result.error("记录工时异常：" + e.getMessage());
        }
    }

    /**
     * 查询工人工时列表
     * @param workerId 工人ID
     * @return 工时列表
     */
    @GetMapping("/worker/hour/list")
    public Result<List<WorkerWorkHour>> getWorkerWorkHour(@RequestParam Long workerId) {
        List<WorkerWorkHour> hourList = workerWorkHourService.getWorkHourListByWorkerId(workerId);
        return Result.success(hourList);
    }
}