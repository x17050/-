package com.repair.order.common;

import lombok.Data;

/**
 * 统一返回结果
 */
@Data
public class Result<T> {
    // 状态码：200成功，500失败
    private Integer code;
    // 提示信息
    private String msg;
    // 数据
    private T data;

    // 三参数构造器，供静态工厂方法使用
    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    // 成功（无数据）
    public static <T> Result<T> success() {
        return new Result<>(200, "操作成功", null);
    }

    // 成功（有数据）
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "操作成功", data);
    }

    // 失败
    public static <T> Result<T> error(String msg) {
        return new Result<>(500, msg, null);
    }
}