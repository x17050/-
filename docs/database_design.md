# Database Design

## user

用户信息表

字段：

id  
username  
password  
role  

---

## repair_order

维修订单表

字段：

id  
title  
description  
status  
create_time  

---

## approval_record

审批记录表

字段：

id  
order_id  
approver  
status  
approve_time
